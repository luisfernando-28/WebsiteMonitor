package com.example.websitemonitor.service;

import com.example.websitemonitor.dto.ExternalPostDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalPostDTO buscarPostPorId(Long id) {
        String url = "https://jsonplaceholder.typicode.com/posts/" + id;
        return restTemplate.getForObject(url, ExternalPostDTO.class);
    }
}