package br.com.Alisson.bar.api_bar.model;

import java.math.BigDecimal;
import java.time.LocalDateTime; 
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference; 

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "conta")
    private List<ItemConsumido> itensConsumidos;

    private boolean couvertHabilitado = false; 

    private BigDecimal valorTotalPago = BigDecimal.ZERO; 
    
    private LocalDateTime dataFechamento; // <<<---- MUDANÇA
}