<?php
// Database connection settings
$host = 'localhost'; // Database host
$dbname = 'hotel_booking'; // Database name
$username = 'root'; // Database username
$password = ''; // Database password

// Function to establish a database connection
function getDBConnection() {
    global $host, $dbname, $username, $password;
    try {
        $conn = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        return $conn;
    } catch (PDOException $e) {
        echo "Connection failed: " . $e->getMessage();
        return null;
    }
}
?>