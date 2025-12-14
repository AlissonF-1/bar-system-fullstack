package br.com.Alisson.bar.api_bar.model;

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
@Table(name = "mesas")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    private int numeroPessoas;

    @Enumerated(EnumType.STRING)
    private StatusMesa status;
}