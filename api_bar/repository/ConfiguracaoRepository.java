package br.com.Alisson.bar.api_bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.Alisson.bar.api_bar.model.Configuracao;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {

    @Query("SELECT c FROM Configuracao c ORDER BY c.id LIMIT 1")
    Configuracao findFirst();
}