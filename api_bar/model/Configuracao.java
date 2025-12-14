package br.com.Alisson.bar.api_bar.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "configuracao")
public class Configuracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private BigDecimal percentualGorjetaComida = BigDecimal.valueOf(0.10);

    private BigDecimal percentualGorjetaBebida = BigDecimal.valueOf(0.10);

    private BigDecimal precoCouvert = BigDecimal.ZERO; // Preço por pessoa
}