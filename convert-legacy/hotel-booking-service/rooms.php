<?php
require_once 'includes/db.php';
require_once 'includes/functions.php';

// Fetch available rooms from the database
$rooms = getAvailableRooms();

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Available Rooms</title>
</head>
<body>
    <?php include 'includes/header.php'; ?>

    <div class="container">
        <h1 class="mt-5">Available Rooms</h1>
        <div class="row">
            <?php foreach ($rooms as $room): ?>
                <div class="col-md-4">
                    <div class="card mb-4">
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