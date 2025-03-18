package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;
import java.util.Base64;

@RestController
@RequestMapping("/vulnerable/files")
public class VulnerableFileController {

    @Autowired
    private DataSource dataSource;

    // Vulnerable to SQL Injection
    @GetMapping("/records/{userId}")
    public String getUserRecords(@PathVariable String userId) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            // Vulnerable SQL query - direct string concatenation
            String query = "SELECT * FROM records WHERE user_id = '" + userId + "'";
            return stmt.executeQuery(query).toString();
        }
    }

    // Vulnerable to Path Traversal
    @GetMapping("/download")
    public String downloadFile(@RequestParam String filename) {
        try {
            // Vulnerable to path traversal - no path validation
            File file = new File("/tmp/" + filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        } catch (IOException e) {
            return "Error: " + e.getMessage();