<?php
require_once 'includes/config.php';
require_once 'includes/db.php';
require_once 'includes/functions.php';
require_once 'includes/header.php';

// Fetch available rooms for display
$rooms = getAvailableRooms();

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotel Booking Service</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Welcome to Our Hotel Booking Service</h1>
        <p>Find and book your perfect stay with us!</p>

        <h2>Available Rooms</h2>
        <div class="row">
            <?php foreach ($rooms as $room): ?>
                <div class="col-md-4">
                    <div class="card">
                        <img src="images/<?php echo $room['image']; ?>" class="card-img-top" alt="<?php echo $room['name']; ?>">
                        <div class="card-body">
                            <h5 class="card-title"><?php echo $room['name']; ?></h5>
                            <p class="card-text">Price: $<?php echo $room['price']; ?> per night</p>
                            <a href="booking.php?room_id=<?php echo $room['id']; ?>" class="btn btn-primary">Book Now</a>
                        </div>
                    </div>
                </div>
            <?php endforeach; ?>
        </div>
    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>