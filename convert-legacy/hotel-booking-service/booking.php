<?php
require_once 'includes/db.php';
require_once 'includes/functions.php';

session_start();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $room_id = $_POST['room_id'];
    $check_in = $_POST['check_in'];
    $check_out = $_POST['check_out'];
    $guest_name = $_POST['guest_name'];
    $guest_email = $_POST['guest_email'];

    $errors = [];

    if (empty($room_id) || empty($check_in) || empty($check_out) || empty($guest_name) || empty($guest_email)) {
        $errors[] = 'All fields are required.';
    }

    if (empty($errors)) {
        $db = db_connect();
        $booking_id = createBooking($db, $room_id, $check_in, $check_out, $guest_name, $guest_email);

        if ($booking_id) {
            $_SESSION['booking_id'] = $booking_id;
            header('Location: confirmation.php');
            exit();
        } else {
            $errors[] = 'Booking failed. Please try again.';
        }
    }
}

$rooms = getAvailableRooms(); // Function to fetch available rooms

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Hotel Booking</title>
</head>
<body>
    <?php include 'includes/header.php'; ?>

    <div class="container">
        <h2>Book a Room</h2>

        <?php if (!empty($errors)): ?>
            <div class="alert alert-danger">
                <?php foreach ($errors as $error): ?>
                    <p><?php echo htmlspecialchars($error); ?></p>
                <?php endforeach; ?>
            </div>
        <?php endif; ?>

        <form action="booking.php" method="POST">
            <div class="form-group">
                <label for="room_id">Select Room:</label>
                <select name="room_id" id="room_id" class="form-control" required>
                    <?php foreach ($rooms as $room): ?>
                        <option value="<?php echo $room['id']; ?>"><?php echo htmlspecialchars($room['name']); ?></option>
                    <?php endforeach; ?>
                </select>
            </div>
            <div class="form-group">
                <label for="check_in">Check-in Date:</label>
                <input type="date" name="check_in" id="check_in" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="check_out">Check-out Date:</label>
                <input type="date" name="check_out" id="check_out" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="guest_name">Your Name:</label>
                <input type="text" name="guest_name" id="guest_name" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="guest_email">Your Email:</label>
                <input type="email" name="guest_email" id="guest_email" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Book Now</button>
        </form>
    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>