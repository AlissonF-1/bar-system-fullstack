package br.com.Alisson.bar.api_bar.controller;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.Alisson.bar.api_bar.model.Conta; 
import br.com.Alisson.bar.api_bar.model.ItemConsumido;
import br.com.Alisson.bar.api_bar.model.Mesa;
import br.com.Alisson.bar.api_bar.service.GarcomService;

@RestController
@RequestMapping("/api/garcom") // Todos endpoints do garçom
public class GarcomController {

    @Autowired
    private GarcomService garcomService;

  
    @PostMapping("/mesas/{idMesa}/abrir")
    public ResponseEntity<?> abrirMesa(
            @PathVariable Long idMesa, 
            @RequestParam int pessoas) {
        
        try {
            Mesa mesaAberta = garcomService.abrirMesa(idMesa, pessoas);
            return ResponseEntity.ok(mesaAberta); 
        
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); 
        }
    }

    @PostMapping("/mesas/{idMesa}/adicionar-item")
    public ResponseEntity<?> adicionarItem(
            @PathVariable Long idMesa,
            @RequestParam Long idItem,
            @RequestParam int quantidade) {

        try {
            ItemConsumido itemSalvo = garcomService.adicionarItemNaConta(idMesa, idItem, quantidade);
            return new ResponseEntity<>(itemSalvo, HttpStatus.CREATED);
        
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
   
    @PostMapping("/itens-consumidos/{idItem}/cancelar")
    public ResponseEntity<?> cancelarItem(
            @PathVariable Long idItem,
            @RequestParam String motivo) {

        try {
            ItemConsumido itemCancelado = garcomService.cancelarItemDaConta(idItem, motivo);
            return ResponseEntity.ok(itemCancelado); 
        
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

   
    @PostMapping("/mesas/{idMesa}/couvert")
    public ResponseEntity<?> habilitarCouvert(
            @PathVariable Long idMesa,
            @RequestParam boolean habilitar) {

        try {
            Conta contaAtualizada = garcomService.habilitarCouvert(idMesa, habilitar);
            return ResponseEntity.ok(contaAtualizada); 
        
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @PostMapping("/mesas/{idMesa}/pagar")
    public ResponseEntity<?> registrarPagamento(
            @PathVariable Long idMesa,
            @RequestParam BigDecimal valor) {

        try {
            Conta contaAtualizada = garcomService.registrarPagamento(idMesa, valor);
            return ResponseEntity.ok(contaAtualizada); 
        
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

  
    @PostMapping("/mesas/{idMesa}/fechar")
    public ResponseEntity<?> fecharConta(
            @PathVariable Long idMesa) {

        try {
            Mesa mesaFechada = garcomService.fecharConta(idMesa);
            return ResponseEntity.ok(mesaFechada); 
        
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}