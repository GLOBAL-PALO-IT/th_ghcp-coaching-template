function getAvailableRooms($checkInDate, $checkOutDate) {
    global $db;

    $query = "SELECT * FROM rooms WHERE id NOT IN (
                SELECT room_id FROM bookings 
                WHERE (check_in_date <= ? AND check_out_date >= ?)
              )";
    $stmt = $db->prepare($query);
    $stmt->bind_param("ss", $checkOutDate, $checkInDate);
    $stmt->execute();
    $result = $stmt->get_result();

    return $result->fetch_all(MYSQLI_ASSOC);
}

function createBooking($roomId, $userId, $checkInDate, $checkOutDate) {
    global $db;

    $query = "INSERT INTO bookings (room_id, user_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
    $stmt = $db->prepare($query);
    $stmt->bind_param("iiss", $roomId, $userId, $checkInDate, $checkOutDate);
    return $stmt->execute();
}

function validateBookingData($data) {
    $errors = [];

    if (empty($data['room_id'])) {
        $errors[] = "Room ID is required.";
    }
    if (empty($data['user_id'])) {
        $errors[] = "User ID is required.";
    }
    if (empty($data['check_in_date']) || empty($data['check_out_date'])) {
        $errors[] = "Check-in and check-out dates are required.";
    }

    return $errors;
}

function getBookingDetails($bookingId) {
    global $db;

    $query = "SELECT * FROM bookings WHERE id = ?";
    $stmt = $db->prepare($query);
    $stmt->bind_param("i", $bookingId);
    $stmt->execute();
    $result = $stmt->get_result();

    return $result->fetch_assoc();
}