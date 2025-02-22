package com.example.demo.controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    private final JdbcTemplate jdbcTemplate;

    public HelloWorldController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public Map<String, String> home() {
        return Map.of("message", "Hello from the BFSI-compliant API!");
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return Map.of("status", "ok", "message", "Database connection successful!");
        } catch (Exception e) {
            return Map.of("status", "error", "message", e.getMessage());
        }
    }
}