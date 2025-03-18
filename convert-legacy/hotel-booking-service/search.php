<?php
require_once 'includes/db.php';
require_once 'includes/functions.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $check_in = $_POST['check_in'];
    $check_out = $_POST['check_out'];
    $room_type = $_POST['room_type'];

    $conn = db_connect();
    $available_rooms = search_rooms($conn, $check_in, $check_out, $room_type);
} else {
    $available_rooms = [];
}

function search_rooms($conn, $check_in, $check_out, $room_type) {
    $query = "SELECT * FROM rooms WHERE room_type = ? AND id NOT IN (
                SELECT room_id FROM bookings WHERE (check_in <= ? AND check_out >= ?)
              )";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("sss", $room_type, $check_out, $check_in);
    $stmt->execute();
    $result = $stmt->get_result();
    return $result->fetch_all(MYSQLI_ASSOC);
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Rooms</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <?php include 'includes/header.php'; ?>
    <div class="container">
        <h1>Search Available Rooms</h1>
        <form method="POST" action="search.php">
            <div class="form-group">
                <label for="check_in">Check-in Date:</label>
                <input type="date" class="form-control" id="check_in" name="check_in" required>
            </div>
            <div class="form-group">
                <label for="check_out">Check-out Date:</label>
                <input type="date" class="form-control" id="check_out" name="check_out" required>
            </div>
            <div class="form-group">
                <label for="room_type">Room Type:</label>
                <select class="form-control" id="room_type" name="room_type" required>
                    <option value="single">Single</option>
                    <option value="double">Double</option>
                    <option value="suite">Suite</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>

        <?php if (!empty($available_rooms)): ?>
            <h2>Available Rooms</h2>
            <ul class="list-group">
                <?php foreach ($available_rooms as $room): ?>
                    <li class="list-group-item">
                        Room Type: <?php echo htmlspecialchars($room['room_type']); ?> - Price: $<?php echo htmlspecialchars($room['price']); ?>
                        <a href="booking.php?room_id=<?php echo $room['id']; ?>" class="btn btn-success btn-sm float-right">Book Now</a>
                    </li>
                <?php endforeach; ?>
            </ul>
        <?php elseif ($_SERVER['REQUEST_METHOD'] == 'POST'): ?>
            <p>No rooms available for the selected dates and room type.</p>
        <?php endif; ?>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>