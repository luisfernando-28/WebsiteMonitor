package com.example.websitemonitor.service;

import com.example.websitemonitor.model.Website;
import com.example.websitemonitor.repository.WebsiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteService {

    private final WebsiteRepository repository;

    public WebsiteService(WebsiteRepository repository) {
        this.repository = repository;
    }

    public Website create(Website website) {
        return repository.save(website);
    }

    public List<Website> findAll() {
        return repository.findAll();
    }
}