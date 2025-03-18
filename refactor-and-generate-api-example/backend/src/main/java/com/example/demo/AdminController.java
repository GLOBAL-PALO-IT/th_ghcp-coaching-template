package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DataSource dataSource;

    private static final Map<String, String> USERS = new HashMap<>();
    static {
        USERS.put("admin", "admin123");
    }

    @GetMapping("/user-data/{userId}")
    public String getUserData(@PathVariable String userId) throws Exception {
        File userFile = new File("/tmp/users/" + userId + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(userFile));
        return reader.readLine();
    }

    @PostMapping("/feedback")
    public String storeFeedback(@RequestParam String message) {
        return "<div>Latest feedback: " + message + "</div>";
    }

    // Mass Assignment vulnerability
    @PostMapping("/update-user")
    public void updateUser(@RequestBody User user) {
        saveUserToDatabase(user);
    }

    @GetMapping("/download")
    public String downloadFile(@RequestParam String filename) throws Exception {
        File file = new File("/app/files/" + filename);
        return new BufferedReader(new FileReader(file)).readLine();
    }

    @GetMapping("/config")
    public Map<String, String> getSystemConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("dbPassword", "super_secret_password");
        config.put("apiKey", "sk_live_12345abcdef");
        return config;
    }

    @PostMapping("/exec-query")
    public String executeQuery(@RequestParam String tableName) throws Exception {
        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM " + tableName).toString();
    }

    private void saveUserToDatabase(User user) {
    }
}
