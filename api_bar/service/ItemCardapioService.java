package br.com.Alisson.bar.api_bar.service;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Alisson.bar.api_bar.model.ItemCardapio;
import br.com.Alisson.bar.api_bar.repository.ItemCardapioRepository;

@Service
public class ItemCardapioService {

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    public ItemCardapio cadastrarItem(ItemCardapio item) {
        return itemCardapioRepository.save(item);
    }

   
    public List<ItemCardapio> listarTodos() {
        return itemCardapioRepository.findAll();
    }

  
    public ItemCardapio atualizarItem(Long idItem, ItemCardapio dadosAtualizacao) {
        
        ItemCardapio itemExistente = itemCardapioRepository.findById(idItem)
            .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado com id: " + idItem));

        if (dadosAtualizacao.getNome() != null) {
            itemExistente.setNome(dadosAtualizacao.getNome());
        }
        if (dadosAtualizacao.getCategoria() != null) {
            itemExistente.setCategoria(dadosAtualizacao.getCategoria());
        }
        if (dadosAtualizacao.getPreco() != null) {
            itemExistente.setPreco(dadosAtualizacao.getPreco());
        }
        return itemCardapioRepository.save(itemExistente);
    }
}