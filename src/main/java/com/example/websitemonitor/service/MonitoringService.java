package com.example.websitemonitor.service;

import com.example.websitemonitor.dto.WebsiteStatusDTO;
import com.example.websitemonitor.model.Website;
import com.example.websitemonitor.model.WebsiteCheck;
import com.example.websitemonitor.repository.WebsiteCheckRepository;
import com.example.websitemonitor.repository.WebsiteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MonitoringService {

    private final WebsiteRepository websiteRepository;
    private final WebsiteCheckRepository checkRepository;

    public MonitoringService(WebsiteRepository websiteRepository,
                             WebsiteCheckRepository checkRepository) {
        this.websiteRepository = websiteRepository;
        this.checkRepository = checkRepository;
    }

    // 🔁 EXECUTA A CADA 10 SEGUNDOS (PARA TESTES)
    @Scheduled(fixedRate = 10000)
    public void checkAllWebsites() {
        System.out.println(">>> CRON EXECUTADO! Iniciando verificação...");

        List<Website> websites = websiteRepository.findAll();

        if (websites.isEmpty()) {
            System.out.println("Nenhum site cadastrado. Nada para verificar.");
        }

        for (Website site : websites) {
            System.out.println("Verificando site: " + site.getUrl());
            WebsiteCheck check = performCheck(site);
            checkRepository.save(check);
            System.out.println("Resultado → online: " + check.isOnline() +
                    " | status: " + check.getStatusCode() +
                    " | tempo: " + check.getResponseTimeMs() + "ms");
        }
    }

    // 🔍 Verifica UM site
    private WebsiteCheck performCheck(Website site) {
        WebsiteCheck check = new WebsiteCheck();
        check.setWebsite(site);
        check.setCheckedAt(LocalDateTime.now());

        long start = System.currentTimeMillis();
        boolean online;
        int statusCode = 0;
        String errorMessage = null;

        try {
            URL url = new URL(site.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");

            statusCode = connection.getResponseCode();
            online = (statusCode >= 200 && statusCode < 400);

        } catch (Exception e) {
            online = false;
            errorMessage = e.getMessage();
        }

        long responseTime = System.currentTimeMillis() - start;

        check.setOnline(online);
        check.setStatusCode(statusCode);
        check.setResponseTimeMs(responseTime);
        check.setErrorMessage(errorMessage);

        return check;
    }

    // 📜 Histórico completo de um site
    public List<WebsiteCheck> getHistoryForWebsite(Long websiteId) {
        return checkRepository.findByWebsiteIdOrderByCheckedAtDesc(websiteId);
    }

    // 📊 Status atual + uptime de um site
    public WebsiteStatusDTO getStatusForWebsite(Long websiteId) {
        var websiteOpt = websiteRepository.findById(websiteId);
        if (websiteOpt.isEmpty()) {
            throw new RuntimeException("Website não encontrado com id: " + websiteId);
        }

        var website = websiteOpt.get();

        WebsiteCheck lastCheck = checkRepository.findFirstByWebsiteIdOrderByCheckedAtDesc(websiteId);
        long totalChecks = checkRepository.countByWebsiteId(websiteId);
        long totalOnline = checkRepository.countByWebsiteIdAndOnlineTrue(websiteId);

        WebsiteStatusDTO dto = new WebsiteStatusDTO();
        dto.setWebsiteId(website.getId());
        dto.setWebsiteName(website.getName());
        dto.setWebsiteUrl(website.getUrl());
        dto.setTotalChecks(totalChecks);
        dto.setTotalOnline(totalOnline);

        if (totalChecks > 0) {
            double uptime = (totalOnline * 100.0) / totalChecks;
            dto.setUptimePercent(Math.round(uptime * 100.0) / 100.0); // 2 casas decimais
        } else {
            dto.setUptimePercent(null);
        }

        if (lastCheck != null) {
            dto.setLastCheckedAt(lastCheck.getCheckedAt());
            dto.setLastOnline(lastCheck.isOnline());
            dto.setLastStatusCode(lastCheck.getStatusCode());
            dto.setLastResponseTimeMs(lastCheck.getResponseTimeMs());
        }

        return dto;
    }
}