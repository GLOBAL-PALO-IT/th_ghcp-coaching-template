package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/performance-examples")
public class PerformanceExampleController {

    @Autowired
    private DataSource dataSource;

    // Inefficient SQL query - N+1 problem
    @GetMapping("/users-orders")
    public List<Map<String, Object>> getUsersWithOrders() throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        Connection conn = dataSource.getConnection();

        Statement stmt = conn.createStatement();
        ResultSet users = stmt.executeQuery("SELECT * FROM users");

        while (users.next()) {
            Map<String, Object> userMap = new HashMap<>();
            int userId = users.getInt("id");
            userMap.put("userId", userId);

            // Inefficient: Separate query for each user's orders
            Statement orderStmt = conn.createStatement();
            ResultSet orders = orderStmt.executeQuery(
                    "SELECT * FROM orders WHERE user_id = " + userId);

            List<Map<String, Object>> userOrders = new ArrayList<>();
            while (orders.next()) {
                userOrders.add(new HashMap<>());
            }
            userMap.put("orders", userOrders);
            result.add(userMap);
        }
        return result;
    }

    // Inefficient string concatenation
    @GetMapping("/generate-report")
    public String generateReport() {
        String report = "";
        for (int i = 0; i < 1000; i++) {
            report = report + "Line " + i + "\n";
        }
        return report;
    }

    // Inefficient collection initialization
    @GetMapping("/process-data")
    public List<Integer> processData() {
        // Non-optimal initial capacity
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            numbers.add(i);
        }

        // Inefficient list to array conversion
        Integer[] arr = numbers.toArray(new Integer[0]);

        // Unnecessary object creation
        List<Integer> result = new ArrayList<>();
        for (Integer num : arr) {
            result.add(new Integer(num));
        }
        return result;
    }

    // Memory leak potential
    private Map<String, byte[]> cache = new HashMap<>();

    @PostMapping("/cache-data")
    public void cacheData(@RequestParam String key, @RequestParam String value) {
        // Unbounded cache growth
        cache.put(key, value.getBytes());
    }

    // Inefficient SELECT * usage
    @GetMapping("/all-products")
    public List<Map<String, Object>> getAllProducts() throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement()) {
            // Bad: Using SELECT * instead of specific columns
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("data", rs.getObject(1));
                result.add(row);
            }
        }
        return result;
    }

    // Inefficient IN clause with subquery
    @GetMapping("/active-users")
    public List<String> getActiveUsers() throws SQLException {
        List<String> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement()) {
            // Bad: Inefficient subquery in IN clause
            ResultSet rs = stmt.executeQuery(
                    "SELECT username FROM users WHERE id IN " +
                            "(SELECT user_id FROM orders WHERE status IN " +
                            "(SELECT id FROM order_status WHERE name = 'active'))");
            while (rs.next()) {
                users.add(rs.getString("username"));
            }
        }
        return users;
    }

    // Inefficient LIKE with leading wildcard
    @GetMapping("/search-products")
    public List<String> searchProducts(@RequestParam String term) throws SQLException {
        List<String> products = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement()) {
            // Bad: LIKE with leading wildcard prevents index usage
            ResultSet rs = stmt.executeQuery(
                    "SELECT name FROM products WHERE name LIKE '%" + term + "%'");
            while (rs.next()) {
                products.add(rs.getString("name"));
            }
        }
        return products;
    }

    // Unoptimized pagination
    @GetMapping("/products-page")
    public List<String> getProductsPage(@RequestParam int page, @RequestParam int size) throws SQLException {
        List<String> products = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement()) {
            // Bad: Inefficient OFFSET pagination
            ResultSet rs = stmt.executeQuery(
                    "SELECT name FROM products ORDER BY name " +
                            "OFFSET " + (page * size) + " ROWS FETCH NEXT " + size + " ROWS ONLY");
            while (rs.next()) {
                products.add(rs.getString("name"));
            }
        }
        return products;
    }

    // Cartesian product (cross join) issue
    @GetMapping("/user-roles")
    public List<Map<String, Object>> getUserRoles() throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement()) {
            // Bad: Missing JOIN condition creates cartesian product
            ResultSet rs = stmt.executeQuery(
                    "SELECT users.name, roles.role_name FROM users, roles");
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("user", rs.getString("name"));
                row.put("role", rs.getString("role_name"));
                result.add(row);
            }
        }
        return result;
    }

    // Multiple aggregate functions in one query
    @GetMapping("/order-stats")
    public Map<String, Object> getOrderStats() throws SQLException {
        Map<String, Object> stats = new HashMap<>();
        try (Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement()) {
            // Bad: Multiple separate queries for aggregates
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM orders");
            rs1.next();
            stats.put("count", rs1.getInt(1));

            ResultSet rs2 = stmt.executeQuery("SELECT AVG(total) FROM orders");
            rs2.next();
            stats.put("average", rs2.getDouble(1));

            ResultSet rs3 = stmt.executeQuery("SELECT MAX(total) FROM orders");
            rs3.next();
            stats.put("max", rs3.getDouble(1));
        }
        return stats;
    }
}
