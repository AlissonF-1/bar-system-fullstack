package br.com.Alisson.bar.api_bar.model; 

import java.math.BigDecimal; 
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data; 

@Data 
@Entity 
@Table(name = "itens_cardapio") 
public class ItemCardapio {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING) 
    private Categoria categoria;

    private BigDecimal preco;

}