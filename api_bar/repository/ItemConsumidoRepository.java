package br.com.Alisson.bar.api_bar.repository;

import java.math.BigDecimal; 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.Alisson.bar.api_bar.controller.RelatorioFaturamentoItemDTO; 
import br.com.Alisson.bar.api_bar.controller.RelatorioItemVendidoDTO; 
import br.com.Alisson.bar.api_bar.model.ItemConsumido;

@Repository
public interface ItemConsumidoRepository extends JpaRepository<ItemConsumido, Long> {
    
    
    @Query("SELECT new br.com.Alisson.bar.api_bar.controller.RelatorioItemVendidoDTO(" +
           "  ic.itemCardapio.nome, " +
           "  SUM(ic.quantidade) " +
           ") " +
           "FROM ItemConsumido ic " +
           "WHERE ic.status = 'ATIVO' " +
           "GROUP BY ic.itemCardapio.nome " +
           "ORDER BY SUM(ic.quantidade) DESC")
    List<RelatorioItemVendidoDTO> findItensMaisVendidos();

  
    
    @Query("SELECT new br.com.Alisson.bar.api_bar.controller.RelatorioFaturamentoItemDTO(" +
           "  ic.itemCardapio.nome, " +
           "  SUM(ic.quantidade * ic.itemCardapio.preco) " + // <<<---- A LÓGICA MUDOU AQUI
           ") " +
           "FROM ItemConsumido ic " +
           "WHERE ic.status = 'ATIVO' " + 
           "GROUP BY ic.itemCardapio.nome " + 
           "ORDER BY SUM(ic.quantidade * ic.itemCardapio.preco) DESC") // Ordena pelo faturamento
    List<RelatorioFaturamentoItemDTO> findItensMaiorFaturamento();
}