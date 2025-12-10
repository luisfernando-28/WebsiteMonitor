package com.example.websitemonitor.controller;

import com.example.websitemonitor.dto.ViaCepResponse;
import com.example.websitemonitor.service.ViaCepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viacep")
public class ViaCepController {

    private final ViaCepService viaCepService;

    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepResponse> buscarEndereco(@PathVariable("cep") String cep) {
        ViaCepResponse endereco = viaCepService.buscarEnderecoPorCep(cep);
        return ResponseEntity.ok(endereco);
    }
}