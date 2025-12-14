package br.com.Alisson.bar.api_bar.controller; 

import java.math.BigDecimal;

public class RelatorioFaturamentoItemDTO {

    private String nomeItem;
    private BigDecimal faturamentoTotal;

    public RelatorioFaturamentoItemDTO(String nomeItem, BigDecimal faturamentoTotal) {
        this.nomeItem = nomeItem;
        this.faturamentoTotal = faturamentoTotal;
    }

    // Getters
    public String getNomeItem() {
        return nomeItem;
    }

    public BigDecimal getFaturamentoTotal() {
        return faturamentoTotal;
    }
}