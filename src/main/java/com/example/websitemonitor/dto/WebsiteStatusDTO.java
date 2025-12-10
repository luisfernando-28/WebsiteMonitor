package com.example.websitemonitor.dto;

import java.time.LocalDateTime;

public class WebsiteStatusDTO {

    private Long websiteId;
    private String websiteName;
    private String websiteUrl;

    private LocalDateTime lastCheckedAt;
    private Boolean lastOnline;
    private Integer lastStatusCode;
    private Long lastResponseTimeMs;

    private long totalChecks;
    private long totalOnline;
    private Double uptimePercent; // ex: 95.5

    public WebsiteStatusDTO() {
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

    public LocalDateTime getLastCheckedAt() {
        return lastCheckedAt;
    }

    public void setLastCheckedAt(LocalDateTime lastCheckedAt) {
        this.lastCheckedAt = lastCheckedAt;
    }

    public Boolean getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(Boolean lastOnline) {
        this.lastOnline = lastOnline;
    }

    public Integer getLastStatusCode() {
        return lastStatusCode;
    }

    public void setLastStatusCode(Integer lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    public Long getLastResponseTimeMs() {
        return lastResponseTimeMs;
    }

    public void setLastResponseTimeMs(Long lastResponseTimeMs) {
        this.lastResponseTimeMs = lastResponseTimeMs;
    }

    public long getTotalChecks() {
        return totalChecks;
    }

    public void setTotalChecks(long totalChecks) {
        this.totalChecks = totalChecks;
    }

    public long getTotalOnline() {
        return totalOnline;
    }

    public void setTotalOnline(long totalOnline) {
        this.totalOnline = totalOnline;
    }

    public Double getUptimePercent() {
        return uptimePercent;
    }

    public void setUptimePercent(Double uptimePercent) {
        this.uptimePercent = uptimePercent;
    }
}