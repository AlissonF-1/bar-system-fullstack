package br.com.Alisson.bar.api_bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Alisson.bar.api_bar.model.ItemCardapio;

@Repository // Opcional, mas boa prática para repositórios
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {

    
    
}