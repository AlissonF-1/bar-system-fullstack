package br.com.Alisson.bar.api_bar.model;

import com.fasterxml.jackson.annotation.JsonBackReference; 

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "itens_consumidos")
public class ItemConsumido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "item_cardapio_id")
    private ItemCardapio itemCardapio;

    private int quantidade;

    @Enumerated(EnumType.STRING)
    private StatusItemConsumido status;

    private String motivoCancelamento;
}