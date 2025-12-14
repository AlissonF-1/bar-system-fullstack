package br.com.Alisson.bar.api_bar.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime; 
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.Alisson.bar.api_bar.controller.RelatorioFaturamentoItemDTO; 
import br.com.Alisson.bar.api_bar.controller.RelatorioItemVendidoDTO;

import br.com.Alisson.bar.api_bar.model.Configuracao;
import br.com.Alisson.bar.api_bar.model.Mesa; 
import br.com.Alisson.bar.api_bar.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/configuracao")
    public ResponseEntity<Configuracao> definirConfiguracao(
            @RequestParam(required = false) BigDecimal couvert,
            @RequestParam(required = false) BigDecimal gorjetaBebida,
            @RequestParam(required = false) BigDecimal gorjetaComida) {
        
        Configuracao configAtualizada = adminService.atualizarConfiguracao(
            couvert, gorjetaBebida, gorjetaComida
        );
        
        return ResponseEntity.ok(configAtualizada);
    }

    @PostMapping("/mesas")
    public ResponseEntity<Mesa> cadastrarMesa(@RequestBody Mesa mesa) {
        Mesa novaMesa = adminService.cadastrarMesa(mesa);
        return new ResponseEntity<>(novaMesa, HttpStatus.CREATED);
    }

    @GetMapping("/mesas")
    public ResponseEntity<List<Mesa>> listarMesas() {
        List<Mesa> mesas = adminService.listarMesas();
        return ResponseEntity.ok(mesas);
    }

    @PutMapping("/mesas/{idMesa}")
    public ResponseEntity<?> atualizarMesa(
            @PathVariable Long idMesa, 
            @RequestBody Mesa mesaDetalhes) {
        try {
            Mesa mesaAtualizada = adminService.atualizarMesa(idMesa, mesaDetalhes);
            return ResponseEntity.ok(mesaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/mesas/{idMesa}")
    public ResponseEntity<?> deletarMesa(@PathVariable Long idMesa) {
        try {
            adminService.deletarMesa(idMesa);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/relatorios/faturamento")
    public ResponseEntity<BigDecimal> getFaturamento(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        
        BigDecimal faturamento = adminService.relatorioFaturamento(inicio, fim);
        return ResponseEntity.ok(faturamento);
    }

    @GetMapping("/relatorios/itens-mais-vendidos")
    public ResponseEntity<List<RelatorioItemVendidoDTO>> getItensMaisVendidos() {
        List<RelatorioItemVendidoDTO> relatorio = adminService.relatorioItensMaisVendidos();
        return ResponseEntity.ok(relatorio);
    }


    @GetMapping("/relatorios/itens-faturamento")
    public ResponseEntity<List<RelatorioFaturamentoItemDTO>> getItensMaiorFaturamento() {
        List<RelatorioFaturamentoItemDTO> relatorio = adminService.relatorioFaturamentoPorItem();
        return ResponseEntity.ok(relatorio);
    }
}