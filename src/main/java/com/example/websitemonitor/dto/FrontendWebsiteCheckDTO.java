package com.example.websitemonitor.dto;

import java.time.LocalDateTime;

public class FrontendWebsiteCheckDTO {

    private Long id;
    private LocalDateTime checkedAt;
    private Boolean online;
    private Integer statusCode;
    private Long responseTimeMs;
    private String errorMessage;

    public FrontendWebsiteCheckDTO() {
    }

    public FrontendWebsiteCheckDTO(Long id, LocalDateTime checkedAt, Boolean online,
                                   Integer statusCode, Long responseTimeMs, String errorMessage) {
        this.id = id;
        this.checkedAt = checkedAt;
        this.online = online;
        this.statusCode = statusCode;
        this.responseTimeMs = responseTimeMs;
        this.errorMessage = errorMessage;
    }

    // Getters e setters

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}