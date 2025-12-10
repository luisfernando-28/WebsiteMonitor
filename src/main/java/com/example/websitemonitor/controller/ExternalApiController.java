package com.example.websitemonitor.controller;

import com.example.websitemonitor.dto.ExternalPostDTO;
import com.example.websitemonitor.service.ExternalApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external")
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    public ExternalApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<ExternalPostDTO> buscarPost(@PathVariable Long id) {
        ExternalPostDTO post = externalApiService.buscarPostPorId(id);
        return ResponseEntity.ok(post);
    }
}
