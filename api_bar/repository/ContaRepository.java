package br.com.Alisson.bar.api_bar.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.Alisson.bar.api_bar.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    
    Optional<Conta> findByMesaId(Long mesaId); 

    @Query("SELECT SUM(c.valorTotalPago) FROM Conta c " +
           "WHERE c.dataFechamento BETWEEN :inicio AND :fim")
    BigDecimal calcularFaturamentoPorPeriodo(LocalDateTime inicio, LocalDateTime fim);
}