package br.com.Alisson.bar.api_bar.service;

import java.math.BigDecimal;
import java.time.LocalDateTime; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Alisson.bar.api_bar.controller.RelatorioFaturamentoItemDTO; 
import br.com.Alisson.bar.api_bar.controller.RelatorioItemVendidoDTO; 

import br.com.Alisson.bar.api_bar.model.Configuracao;
import br.com.Alisson.bar.api_bar.model.Mesa;
import br.com.Alisson.bar.api_bar.model.StatusMesa;
import br.com.Alisson.bar.api_bar.repository.ConfiguracaoRepository;
import br.com.Alisson.bar.api_bar.repository.ContaRepository;
import br.com.Alisson.bar.api_bar.repository.ItemConsumidoRepository; 
import br.com.Alisson.bar.api_bar.repository.MesaRepository;

@Service
public class AdminService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private MesaRepository mesaRepository;
    
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ItemConsumidoRepository itemConsumidoRepository;

    public Configuracao atualizarConfiguracao(BigDecimal precoCouvert, 
                                              BigDecimal gorjetaBebida, 
                                              BigDecimal gorjetaComida) {
        
        Configuracao config = configuracaoRepository.findFirst();

        if (config == null) {
            config = new Configuracao();
        }

        if (precoCouvert != null) {
            config.setPrecoCouvert(precoCouvert);
        }
        if (gorjetaBebida != null) {
            config.setPercentualGorjetaBebida(gorjetaBebida);
        }
        if (gorjetaComida != null) {
            config.setPercentualGorjetaComida(gorjetaComida);
        }

        return configuracaoRepository.save(config);
    }
    
    public Mesa cadastrarMesa(Mesa mesa) {
        mesa.setStatus(StatusMesa.FECHADA);
        mesa.setNumeroPessoas(0);
        return mesaRepository.save(mesa);
    }

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Mesa atualizarMesa(Long idMesa, Mesa mesaDetalhes) {
        Mesa mesa = mesaRepository.findById(idMesa)
            .orElseThrow(() -> new RuntimeException("Mesa não encontrada com id: " + idMesa));

        mesa.setNumero(mesaDetalhes.getNumero());
        
        return mesaRepository.save(mesa);
    }

    public void deletarMesa(Long idMesa) {
        Mesa mesa = mesaRepository.findById(idMesa)
            .orElseThrow(() -> new RuntimeException("Mesa não encontrada com id: " + idMesa));

        if (mesa.getStatus() == StatusMesa.ABERTA) {
            throw new RuntimeException("Não é possível deletar uma mesa que está aberta");
        }
        
        mesaRepository.delete(mesa);
    }

    public BigDecimal relatorioFaturamento(LocalDateTime inicio, LocalDateTime fim) {
        BigDecimal faturamento = contaRepository.calcularFaturamentoPorPeriodo(inicio, fim);
        return (faturamento == null) ? BigDecimal.ZERO : faturamento;
    }

    public List<RelatorioItemVendidoDTO> relatorioItensMaisVendidos() {
        return itemConsumidoRepository.findItensMaisVendidos();
    }

    public List<RelatorioFaturamentoItemDTO> relatorioFaturamentoPorItem() {
        return itemConsumidoRepository.findItensMaiorFaturamento();
    }
}