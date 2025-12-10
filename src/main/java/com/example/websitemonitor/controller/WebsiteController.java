package com.example.websitemonitor.controller;

import com.example.websitemonitor.dto.WebsiteCheckDTO;
import com.example.websitemonitor.dto.WebsiteDTO;
import com.example.websitemonitor.dto.WebsiteStatusDTO;
import com.example.websitemonitor.model.Website;
import com.example.websitemonitor.model.WebsiteCheck;
import com.example.websitemonitor.service.MonitoringService;
import com.example.websitemonitor.service.WebsiteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/websites")
public class WebsiteController {

    private final WebsiteService service;
    private final MonitoringService monitoringService;

    public WebsiteController(WebsiteService service,
                             MonitoringService monitoringService) {
        this.service = service;
        this.monitoringService = monitoringService;
    }

    // Cadastrar um novo site
    @PostMapping
    public ResponseEntity<WebsiteDTO> create(@Valid @RequestBody Website website) {
        Website saved = service.create(website);
        return ResponseEntity.ok(WebsiteDTO.fromEntity(saved));
    }

    // Listar todos os sites cadastrados
    @GetMapping
    public ResponseEntity<List<WebsiteDTO>> listAll() {
        List<Website> list = service.findAll();
        List<WebsiteDTO> dtoList = list.stream()
                .map(WebsiteDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // Histórico de verificações de um site específico
    @GetMapping("/{id}/checks")
    public ResponseEntity<List<WebsiteCheckDTO>> history(@PathVariable Long id) {
        List<WebsiteCheck> checks = monitoringService.getHistoryForWebsite(id);
        List<WebsiteCheckDTO> dtoList = checks.stream()
                .map(WebsiteCheckDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // Status atual + uptime do site
    @GetMapping("/{id}/status")
    public ResponseEntity<WebsiteStatusDTO> status(@PathVariable Long id) {
        WebsiteStatusDTO dto = monitoringService.getStatusForWebsite(id);
        return ResponseEntity.ok(dto);
    }
}