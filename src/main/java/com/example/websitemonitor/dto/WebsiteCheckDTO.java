package com.example.websitemonitor.dto;

import com.example.websitemonitor.model.WebsiteCheck;

import java.time.LocalDateTime;

public class WebsiteCheckDTO {

    private Long id;
    private LocalDateTime checkedAt;
    private boolean online;
    private long responseTimeMs;
    private Integer statusCode;
    private String errorMessage;

    private Long websiteId;
    private String websiteName;
    private String websiteUrl;

    public WebsiteCheckDTO() {
    }

    public WebsiteCheckDTO(Long id,
                           LocalDateTime checkedAt,
                           boolean online,
                           long responseTimeMs,
                           Integer statusCode,
                           String errorMessage,
                           Long websiteId,
                           String websiteName,
                           String websiteUrl) {
        this.id = id;
        this.checkedAt = checkedAt;
        this.online = online;
        this.responseTimeMs = responseTimeMs;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.websiteId = websiteId;
        this.websiteName = websiteName;
        this.websiteUrl = websiteUrl;
    }

    public static WebsiteCheckDTO fromEntity(WebsiteCheck entity) {
        return new WebsiteCheckDTO(
                entity.getId(),
                entity.getCheckedAt(),
                entity.isOnline(),
                entity.getResponseTimeMs(),
                entity.getStatusCode(),
                entity.getErrorMessage(),
                entity.getWebsite() != null ? entity.getWebsite().getId() : null,
                entity.getWebsite() != null ? entity.getWebsite().getName() : null,
                entity.getWebsite() != null ? entity.getWebsite().getUrl() : null
        );
    }

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public long getResponseTimeMs() {
        return responseTimeMs;
    }

    public void setResponseTimeMs(long responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Long websiteId) {
        this.websiteId = websiteId;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}