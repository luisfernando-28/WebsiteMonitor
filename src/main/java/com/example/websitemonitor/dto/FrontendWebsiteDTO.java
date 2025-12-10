package com.example.websitemonitor.dto;

import java.time.LocalDateTime;

public class FrontendWebsiteDTO {

    private Long id;
    private String name;
    private String url;

    // Dados do último check
    private Boolean online;
    private Integer statusCode;
    private Long responseTimeMs;
    private LocalDateTime lastCheckedAt;

    public FrontendWebsiteDTO() {
    }

    public FrontendWebsiteDTO(Long id, String name, String url,
                              Boolean online, Integer statusCode,
                              Long responseTimeMs, LocalDateTime lastCheckedAt) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.online = online;
        this.statusCode = statusCode;
        this.responseTimeMs = responseTimeMs;
        this.lastCheckedAt = lastCheckedAt;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Long getResponseTimeMs() {
        return responseTimeMs;
    }

    public void setResponseTimeMs(Long responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }

    public LocalDateTime getLastCheckedAt() {
        return lastCheckedAt;
    }

    public void setLastCheckedAt(LocalDateTime lastCheckedAt) {
        this.lastCheckedAt = lastCheckedAt;
    }
}