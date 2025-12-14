package br.com.Alisson.bar.api_bar.service; 

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat; 

import br.com.Alisson.bar.api_bar.model.Categoria;
import br.com.Alisson.bar.api_bar.model.Configuracao;
import br.com.Alisson.bar.api_bar.model.Conta;
import br.com.Alisson.bar.api_bar.model.ItemConsumido;
import br.com.Alisson.bar.api_bar.model.StatusItemConsumido;
import br.com.Alisson.bar.api_bar.repository.ConfiguracaoRepository;
import br.com.Alisson.bar.api_bar.repository.ContaRepository;

@Service
public class CalculoContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public static record DetalhesConta(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal subTotalComida,
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal subTotalBebida,
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal gorjetaComida,
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal gorjetaBebida, 
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal valorCouvert,
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal totalGeral,
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal totalPago,
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal saldoPendente
    ) {}


    public DetalhesConta calcularDetalhesConta(Long idMesa) {
        
        Conta conta = contaRepository.findByMesaId(idMesa)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        
        Configuracao config = configuracaoRepository.findFirst();
        if (config == null) {
            throw new RuntimeException("Configuração do sistema não encontrada");
        }

        BigDecimal subTotalComida = BigDecimal.ZERO;
        BigDecimal subTotalBebida = BigDecimal.ZERO;

        for (ItemConsumido item : conta.getItensConsumidos()) {
            if (item.getStatus() == StatusItemConsumido.ATIVO) {
                BigDecimal precoItem = item.getItemCardapio().getPreco();
                BigDecimal quantidade = new BigDecimal(item.getQuantidade());
                BigDecimal totalItem = precoItem.multiply(quantidade);

                if (item.getItemCardapio().getCategoria() == Categoria.COMIDA) {
                    subTotalComida = subTotalComida.add(totalItem);
                } else {
                    subTotalBebida = subTotalBebida.add(totalItem);
                }
            }
        }

        BigDecimal gorjetaComida = subTotalComida.multiply(config.getPercentualGorjetaComida());
        BigDecimal gorjetaBebida = subTotalBebida.multiply(config.getPercentualGorjetaBebida()); 

        BigDecimal valorCouvert = BigDecimal.ZERO;
        if (conta.isCouvertHabilitado()) { 
            BigDecimal pessoas = new BigDecimal(conta.getMesa().getNumeroPessoas());
            valorCouvert = config.getPrecoCouvert().multiply(pessoas);
        }

        BigDecimal totalGeral = subTotalComida.add(subTotalBebida)
                                .add(gorjetaComida).add(gorjetaBebida)
                                .add(valorCouvert);
        
        BigDecimal totalPago = conta.getValorTotalPago();
        BigDecimal saldoPendente = totalGeral.subtract(totalPago);

        return new DetalhesConta(
            subTotalComida, subTotalBebida, gorjetaComida, gorjetaBebida, 
            valorCouvert, totalGeral, totalPago, saldoPendente
        );
    }
}