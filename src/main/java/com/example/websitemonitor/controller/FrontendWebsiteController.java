package com.example.websitemonitor.controller;

import com.example.websitemonitor.dto.FrontendWebsiteDTO;
import com.example.websitemonitor.dto.FrontendWebsiteCheckDTO;
import com.example.websitemonitor.model.Website;
import com.example.websitemonitor.model.WebsiteCheck;
import com.example.websitemonitor.repository.WebsiteCheckRepository;
import com.example.websitemonitor.repository.WebsiteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/frontend")
public class FrontendWebsiteController {

    private final WebsiteRepository websiteRepository;
    private final WebsiteCheckRepository checkRepository;

    public FrontendWebsiteController(WebsiteRepository websiteRepository,
                                     WebsiteCheckRepository checkRepository) {
        this.websiteRepository = websiteRepository;
        this.checkRepository = checkRepository;
    }

    // =========================
    // 1) LISTA DE SITES + ÚLTIMO STATUS (para o front)
    // GET /frontend/websites
    // =========================
    @GetMapping("/websites")
    public List<FrontendWebsiteDTO> listar() {
        List<Website> websites = websiteRepository.findAll();

        return websites.stream()
                .map(website -> {
                    // pega o último check pelo ID do site
                    WebsiteCheck last = checkRepository
                            .findFirstByWebsiteIdOrderByCheckedAtDesc(website.getId());

                    Boolean online = null;
                    Integer statusCode = null;
                    Long responseTimeMs = null;
                    LocalDateTime checkedAt = null;

                    if (last != null) {
                        online = last.isOnline();
                        statusCode = last.getStatusCode();
                        responseTimeMs = last.getResponseTimeMs();
                        checkedAt = last.getCheckedAt();
                    }

                    return new FrontendWebsiteDTO(
                            website.getId(),
                            website.getName(),
                            website.getUrl(),
                            online,
                            statusCode,
                            responseTimeMs,
                            checkedAt
                    );
                })
                .collect(Collectors.toList());
    }

    // =========================
    // 2) HISTÓRICO COMPLETO DE UM SITE
    // GET /frontend/websites/{id}/history
    // =========================
    @GetMapping("/websites/{id}/history")
    public List<FrontendWebsiteCheckDTO> historico(@PathVariable Long id) {

        List<WebsiteCheck> checks =
                checkRepository.findByWebsiteIdOrderByCheckedAtDesc(id);

        return checks.stream()
                // deixamos explícito que "c" é WebsiteCheck
                .map((WebsiteCheck c) -> new FrontendWebsiteCheckDTO(
                        c.getId(),
                        c.getCheckedAt(),
                        c.isOnline(),
                        c.getStatusCode(),
                        c.getResponseTimeMs(),
                        c.getErrorMessage()
                ))
                .collect(Collectors.toList());
    }
}