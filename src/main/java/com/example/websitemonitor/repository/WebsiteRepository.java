package com.example.websitemonitor.repository;

import com.example.websitemonitor.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<Website, Long> {
}