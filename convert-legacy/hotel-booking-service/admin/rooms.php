<?php
require_once '../includes/db.php';
require_once '../includes/functions.php';

session_start();

if (!isset($_SESSION['admin_logged_in'])) {
    header('Location: index.php');
    exit();
}

$db = db_connect();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    if (isset($_POST['add_room'])) {
        $room_type = $_POST['room_type'];
        $price = $_POST['price'];
        $description = $_POST['description'];

        $query = "INSERT INTO rooms (room_type, price, description) VALUES (?, ?, ?)";
        $stmt = $db->prepare($query);
        $stmt->bind_param("sds", $room_type, $price, $description);
        $stmt->execute();
        $stmt->close();
    }

    if (isset($_POST['delete_room'])) {
        $room_id = $_POST['room_id'];

        $query = "DELETE FROM rooms WHERE id = ?";
        $stmt = $db->prepare($query);
        $stmt->bind_param("i", $room_id);
        $stmt->execute();
        $stmt->close();
    }
}

$query = "SELECT * FROM rooms";
$result = $db->query($query);
$rooms = $result->fetch_all(MYSQLI_ASSOC);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/style.css">
    <title>Manage Rooms</title>
</head>
<body>
    <?php include '../includes/header.php'; ?>
    <div class="container">
        <h1>Manage Rooms</h1>
        <form method="POST" action="">
            <div class="form-group">
                <label for="room_type">Room Type</label>
                <input type="text" class="form-control" name="room_type" required>
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <input type="number" class="form-control" name="price" required>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" name="description" required></textarea>
            </div>
            <button type="submit" name="add_room" class="btn btn-primary">Add Room</button>
        </form>

        <h2 class="mt-5">Existing Rooms</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Room Type</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($rooms as $room): ?>
                <tr>
                    <td><?php echo $room['id']; ?></td>
                    <td><?php echo $room['room_type']; ?></td>
                    <td><?php echo $room['price']; ?></td>
                    <td><?php echo $room['description']; ?></td>
                    <td>
                        <form method="POST" action="">
                            <input type="hidden" name="room_id" value="<?php echo $room['id']; ?>">
                            <button type="submit" name="delete_room" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    </div>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/main.js"></script>
</body>
</html>