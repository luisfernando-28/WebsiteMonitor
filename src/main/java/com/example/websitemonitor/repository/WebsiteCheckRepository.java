package com.example.websitemonitor.repository;

import com.example.websitemonitor.model.WebsiteCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebsiteCheckRepository extends JpaRepository<WebsiteCheck, Long> {

    // histórico completo (já existia)
    List<WebsiteCheck> findByWebsiteIdOrderByCheckedAtDesc(Long websiteId);

    // último registro (mais recente)
    WebsiteCheck findFirstByWebsiteIdOrderByCheckedAtDesc(Long websiteId);

    // total de checks de um site
    long countByWebsiteId(Long websiteId);

    // total de checks que estavam online
    long countByWebsiteIdAndOnlineTrue(Long websiteId);
}