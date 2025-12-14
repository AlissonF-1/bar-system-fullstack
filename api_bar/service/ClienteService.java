package br.com.Alisson.bar.api_bar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Alisson.bar.api_bar.controller.ConsultaContaDTO;
import br.com.Alisson.bar.api_bar.model.Conta;
import br.com.Alisson.bar.api_bar.model.ItemConsumido;
import br.com.Alisson.bar.api_bar.repository.ContaRepository;

@Service
public class ClienteService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CalculoContaService calculoContaService;

    public ConsultaContaDTO consultarConta(Long idMesa) {
        
        // 1. Busca a conta
        Conta conta = contaRepository.findByMesaId(idMesa)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada para esta mesa"));

        // 2. Pega a lista de itens
        List<ItemConsumido> itens = conta.getItensConsumidos();

        // 3. Pega os detalhes dos cálculos
        CalculoContaService.DetalhesConta detalhes = calculoContaService.calcularDetalhesConta(idMesa);

        // 4. Retorna o DTO com as duas informações
        return new ConsultaContaDTO(itens, detalhes);
    }
}