document.addEventListener('DOMContentLoaded', function() {
    const bookingForm = document.getElementById('booking-form');
    const roomSelect = document.getElementById('room-select');
    const checkInDate = document.getElementById('check-in-date');
    const checkOutDate = document.getElementById('check-out-date');
    const submitButton = document.getElementById('submit-button');
    const confirmationMessage = document.getElementById('confirmation-message');

    // Handle form submission
    bookingForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const roomId = roomSelect.value;
        const checkIn = checkInDate.value;
        const checkOut = checkOutDate.value;

        if (roomId && checkIn && checkOut) {
            // AJAX request to book the room
            fetch('booking.php', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ roomId, checkIn, checkOut })
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    confirmationMessage.textContent = 'Booking successful! Confirmation ID: ' + data.confirmationId;
                    confirmationMessage.style.color = 'green';
                } else {
                    confirmationMessage.textContent = 'Booking failed: ' + data.message;
                    confirmationMessage.style.color = 'red';
                }
            })
            .catch(error => {
                confirmationMessage.textContent = 'An error occurred: ' + error.message;
                confirmationMessage.style.color = 'red';
            });
        } else {
            confirmationMessage.textContent = 'Please fill in all fields.';
            confirmationMessage.style.color = 'red';
        }
    });
});