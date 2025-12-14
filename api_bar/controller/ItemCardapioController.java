package br.com.Alisson.bar.api_bar.controller;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.Alisson.bar.api_bar.model.ItemCardapio;
import br.com.Alisson.bar.api_bar.service.ItemCardapioService;

@RestController
@RequestMapping("/api/cardapio")
public class ItemCardapioController {

    @Autowired
    private ItemCardapioService itemCardapioService;


    @PostMapping
    public ResponseEntity<ItemCardapio> cadastrarItem(@RequestBody ItemCardapio item) {
        ItemCardapio itemSalvo = itemCardapioService.cadastrarItem(item);
        return new ResponseEntity<>(itemSalvo, HttpStatus.CREATED);
    }

   
    @GetMapping
    public ResponseEntity<List<ItemCardapio>> listarItens() {
        List<ItemCardapio> itens = itemCardapioService.listarTodos();
      
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }


    @PutMapping("/{idItem}")
    public ResponseEntity<?> atualizarItem(
            @PathVariable Long idItem, 
            @RequestBody ItemCardapio dadosAtualizacao) {
        
        try {
            ItemCardapio itemAtualizado = itemCardapioService.atualizarItem(idItem, dadosAtualizacao);
            return ResponseEntity.ok(itemAtualizado);
        
        } catch (RuntimeException e) {
       
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}