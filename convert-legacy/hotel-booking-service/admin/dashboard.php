<?php
session_start();
include('../includes/config.php');
include('../includes/db.php');
include('../includes/functions.php');

$connection = db_connect();
$totalBookings = get_total_bookings($connection);
$totalRooms = get_total_rooms($connection);
$totalAvailableRooms = get_total_available_rooms($connection);

function get_total_bookings($connection) {
    $query = "SELECT COUNT(*) as count FROM bookings";
    $result = mysqli_query($connection, $query);
    $data = mysqli_fetch_assoc($result);
    return $data['count'];
}

function get_total_rooms($connection) {
    $query = "SELECT COUNT(*) as count FROM rooms";
    $result = mysqli_query($connection, $query);
    $data = mysqli_fetch_assoc($result);
    return $data['count'];
}

function get_total_available_rooms($connection) {
    $query = "SELECT COUNT(*) as count FROM rooms WHERE status = 'available'";
    $result = mysqli_query($connection, $query);
    $data = mysqli_fetch_assoc($result);
    return $data['count'];
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/style.css">
    <title>Admin Dashboard</title>
</head>
<body>
    <?php include('../includes/header.php'); ?>
    <div class="container">
        <h1 class="mt-4">Admin Dashboard</h1>
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-header">Total Bookings</div>
                    <div class="card-body">
                        <h5 class="card-title"><?php echo $totalBookings; ?></h5>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-white bg-success mb-3">
                    <div class="card-header">Total Rooms</div>
                    <div class="card-body">
                        <h5 class="card-title"><?php echo $totalRooms; ?></h5>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-white bg-warning mb-3">
                    <div class="card-header">Available Rooms</div>
                    <div class="card-body">
                        <h5 class="card-title"><?php echo $totalAvailableRooms; ?></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/main.js"></script>
</body>
</html>