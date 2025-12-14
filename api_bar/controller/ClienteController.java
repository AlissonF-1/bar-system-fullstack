package br.com.Alisson.bar.api_bar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Alisson.bar.api_bar.service.ClienteService;

@RestController
@RequestMapping("/api/cliente") 
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

  
    @GetMapping("/mesas/{idMesa}")
    public ResponseEntity<?> consultarConta(@PathVariable Long idMesa) {
        try {
            ConsultaContaDTO dto = clienteService.consultarConta(idMesa);
            return ResponseEntity.ok(dto); 
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}