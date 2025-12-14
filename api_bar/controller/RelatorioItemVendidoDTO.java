package br.com.Alisson.bar.api_bar.controller; 

public class RelatorioItemVendidoDTO {

    private String nomeItem;
    private Long quantidadeVendida;

    public RelatorioItemVendidoDTO(String nomeItem, Long quantidadeVendida) {
        this.nomeItem = nomeItem;
        this.quantidadeVendida = quantidadeVendida;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }
}