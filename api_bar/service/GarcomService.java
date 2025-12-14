package br.com.Alisson.bar.api_bar.service;

import java.math.BigDecimal;
import java.time.LocalDateTime; // <<<---- MUDANÇA
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.Alisson.bar.api_bar.model.Conta;
import br.com.Alisson.bar.api_bar.model.ItemCardapio;
import br.com.Alisson.bar.api_bar.model.ItemConsumido;
import br.com.Alisson.bar.api_bar.model.Mesa;
import br.com.Alisson.bar.api_bar.model.StatusItemConsumido;
import br.com.Alisson.bar.api_bar.model.StatusMesa;
import br.com.Alisson.bar.api_bar.repository.ContaRepository;
import br.com.Alisson.bar.api_bar.repository.ItemCardapioRepository;
import br.com.Alisson.bar.api_bar.repository.ItemConsumidoRepository;
import br.com.Alisson.bar.api_bar.repository.MesaRepository;
import br.com.Alisson.bar.api_bar.service.CalculoContaService; 

@Service
public class GarcomService {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    @Autowired
    private ItemConsumidoRepository itemConsumidoRepository;

    @Autowired
    private CalculoContaService calculoContaService; 

    @Transactional
    public Mesa abrirMesa(Long idMesa, int numeroPessoas) {
        
        Mesa mesa = mesaRepository.findById(idMesa)
            .orElseThrow(() -> new RuntimeException("Mesa não encontrada")); 

        if (mesa.getStatus() == StatusMesa.ABERTA) {
            throw new RuntimeException("Esta mesa já está aberta");
        }

        mesa.setNumeroPessoas(numeroPessoas);
        mesa.setStatus(StatusMesa.ABERTA);
        Mesa mesaSalva = mesaRepository.save(mesa);

        Conta novaConta = new Conta();
        novaConta.setMesa(mesaSalva);
        contaRepository.save(novaConta);

        return mesaSalva;
    }

    @Transactional
    public ItemConsumido adicionarItemNaConta(Long idMesa, Long idItemCardapio, int quantidade) {

        Conta conta = contaRepository.findByMesaId(idMesa)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada para esta mesa"));

        if (conta.getMesa().getStatus() == StatusMesa.FECHADA) {
            throw new RuntimeException("Não é possível adicionar itens a uma mesa fechada");
        }
        
        ItemCardapio itemDoCardapio = itemCardapioRepository.findById(idItemCardapio)
            .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado"));

        ItemConsumido novoItem = new ItemConsumido();
        novoItem.setConta(conta);
        novoItem.setItemCardapio(itemDoCardapio);
        novoItem.setQuantidade(quantidade);
        novoItem.setStatus(StatusItemConsumido.ATIVO); 

        return itemConsumidoRepository.save(novoItem);
    }
    
    @Transactional
    public ItemConsumido cancelarItemDaConta(Long idItemConsumido, String motivo) {

        ItemConsumido item = itemConsumidoRepository.findById(idItemConsumido)
            .orElseThrow(() -> new RuntimeException("Item consumido não encontrado"));

        if (item.getConta().getMesa().getStatus() == StatusMesa.FECHADA) {
            throw new RuntimeException("Não é possível cancelar item de uma mesa fechada");
        }

        item.setStatus(StatusItemConsumido.CANCELADO); 
        item.setMotivoCancelamento(motivo); 

        return itemConsumidoRepository.save(item);
    }

    @Transactional
    public Conta habilitarCouvert(Long idMesa, boolean habilitar) {
        
        Conta conta = contaRepository.findByMesaId(idMesa)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada para esta mesa"));

        if (conta.getMesa().getStatus() == StatusMesa.FECHADA) {
            throw new RuntimeException("Não é possível alterar o couvert de uma mesa fechada");
        }

        conta.setCouvertHabilitado(habilitar);

        return contaRepository.save(conta);
    }

    @Transactional
    public Conta registrarPagamento(Long idMesa, BigDecimal valorPagamento) {

        Conta conta = contaRepository.findByMesaId(idMesa)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada para esta mesa"));
        
        if (conta.getMesa().getStatus() == StatusMesa.FECHADA) {
            throw new RuntimeException("Não é possível registrar pagamento para uma mesa fechada");
        }

        BigDecimal novoTotalPago = conta.getValorTotalPago().add(valorPagamento);
        conta.setValorTotalPago(novoTotalPago);

        return contaRepository.save(conta);
    }

    @Transactional
    public Mesa fecharConta(Long idMesa) {
        
        Conta conta = contaRepository.findByMesaId(idMesa)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        
        Mesa mesa = conta.getMesa();
        if (mesa.getStatus() == StatusMesa.FECHADA) {
            throw new RuntimeException("Esta mesa já está fechada");
        }

        CalculoContaService.DetalhesConta detalhes = calculoContaService.calcularDetalhesConta(idMesa);
        
        if (detalhes.saldoPendente().compareTo(BigDecimal.ZERO) > 0) {
            throw new RuntimeException("A conta não pode ser fechada. Saldo pendente: R$ " + detalhes.saldoPendente());
        }

        mesa.setStatus(StatusMesa.FECHADA);
        conta.setDataFechamento(LocalDateTime.now());
        
        contaRepository.save(conta); 
        return mesaRepository.save(mesa); 
    }
}