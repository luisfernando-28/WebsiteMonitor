package com.example.websitemonitor.service;

import com.example.websitemonitor.dto.ViaCepResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate;

    public ViaCepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViaCepResponse buscarEnderecoPorCep(String cep) {
        // remove tudo que não é número (aceita "01001-000" ou "01001000")
        String cepLimpo = cep.replaceAll("\\D", "");

        String url = "https://viacep.com.br/ws/" + cepLimpo + "/json/";

        ViaCepResponse response = restTemplate.getForObject(url, ViaCepResponse.class);

        // Se o ViaCEP devolve {"erro": true}, significa CEP inválido
        if (response != null && Boolean.TRUE.equals(response.getErro())) {
            throw new IllegalArgumentException("CEP inválido: " + cep);
        }

        return response;
    }
}