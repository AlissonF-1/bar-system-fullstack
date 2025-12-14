package br.com.Alisson.bar.api_bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.Alisson.bar.api_bar.model.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    
}