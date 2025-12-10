package com.example.websitemonitor.repository;

import com.example.websitemonitor.model.Website;
import com.example.websitemonitor.model.WebsiteCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebsiteCheckRepository extends JpaRepository<WebsiteCheck, Long> {

    // --- Usado pelo MonitoringService (código antigo) ---

    // Histórico completo por ID (se você ainda usar em algum lugar)
    List<WebsiteCheck> findByWebsiteIdOrderByCheckedAtDesc(Long websiteId);

    // Último check por ID (retorna diretamente o WebsiteCheck, NÃO Optional)
    WebsiteCheck findFirstByWebsiteIdOrderByCheckedAtDesc(Long websiteId);

    long countByWebsiteId(Long websiteId);

    long countByWebsiteIdAndOnlineTrue(Long websiteId);

    // --- Usado pelo FrontendWebsiteController (BFF novo) ---

    // Histórico completo usando a entidade Website
    List<WebsiteCheck> findByWebsiteOrderByCheckedAtDesc(Website website);

    // Último check usando a entidade Website
    WebsiteCheck findFirstByWebsiteOrderByCheckedAtDesc(Website website);
}