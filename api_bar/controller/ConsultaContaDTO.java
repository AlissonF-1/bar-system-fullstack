package br.com.Alisson.bar.api_bar.controller;

import java.util.List;
import br.com.Alisson.bar.api_bar.model.ItemConsumido;
import br.com.Alisson.bar.api_bar.service.CalculoContaService;



public class ConsultaContaDTO {

    private List<ItemConsumido> itensConsumidos;
    private CalculoContaService.DetalhesConta detalhes;

    public ConsultaContaDTO(List<ItemConsumido> itensConsumidos, CalculoContaService.DetalhesConta detalhes) {
        this.itensConsumidos = itensConsumidos;
        this.detalhes = detalhes;
    }

  
    public List<ItemConsumido> getItensConsumidos() {
        return itensConsumidos;
    }

    public CalculoContaService.DetalhesConta getDetalhes() {
        return detalhes;
    }
}