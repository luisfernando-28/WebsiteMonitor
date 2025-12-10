package com.example.websitemonitor.dto;

import com.example.websitemonitor.model.Website;

public class WebsiteDTO {

    private Long id;
    private String name;
    private String url;

    public WebsiteDTO() {
    }

    public WebsiteDTO(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public static WebsiteDTO fromEntity(Website entity) {
        return new WebsiteDTO(
                entity.getId(),
                entity.getName(),
                entity.getUrl()
        );
    }

    // getters e setters

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
}