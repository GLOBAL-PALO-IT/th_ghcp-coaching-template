<?php
session_start();
if (!isset($_SESSION['booking_details'])) {
    header("Location: index.php");
    exit();
}

$bookingDetails = $_SESSION['booking_details'];
unset($_SESSION['booking_details']);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Booking Confirmation</title>
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Booking Confirmation</h1>
        <div class="alert alert-success mt-4">
            <h4>Your booking has been confirmed!</h4>
            <p>Thank you for choosing our hotel. Here are your booking details:</p>
            <ul>
                <li><strong>Name:</strong> <?php echo htmlspecialchars($bookingDetails['name']); ?></li>
                <li><strong>Email:</strong> <?php echo htmlspecialchars($bookingDetails['email']); ?></li>
                <li><strong>Room Type:</strong> <?php echo htmlspecialchars($bookingDetails['room_type']); ?></li>
                <li><strong>Check-in Date:</strong> <?php echo htmlspecialchars($bookingDetails['check_in']); ?></li>
                <li><strong>Check-out Date:</strong> <?php echo htmlspecialchars($bookingDetails['check_out']); ?></li>
                <li><strong>Total Price:</strong> $<?php echo htmlspecialchars($bookingDetails['total_price']); ?></li>
            </ul>
            <p>If you have any questions, feel free to contact us.</p>
        </div>
        <a href="index.php" class="btn btn-primary">Return to Homepage</a>
    </div>
</body>
</html>