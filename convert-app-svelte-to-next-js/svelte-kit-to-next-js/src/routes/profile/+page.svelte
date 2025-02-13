<script lang="ts">
    // Mock data for demonstration purposes
    const profile = {
        name: 'John Doe',
        email: 'john.doe@example.com',
        phone: '123-456-7890',
        address: '123 Main St, Anytown, USA'
    };
</script>

<style>
    .profile {
        font-family: Arial, sans-serif;
        padding: 20px;
    }

    h1 {
        text-align: center;
        color: #333;
    }

    .profile-info {
        max-width: 600px;
        margin: 0 auto;
        border: 1px solid #ddd;
        padding: 20px;
        border-radius: 8px;
        background-color: #f9f9f9;
    }

    .profile-info h2 {
        color: #555;
        border-bottom: 2px solid #ddd;
        padding-bottom: 10px;
        margin-bottom: 20px;
    }

    .profile-info p {
        margin: 10px 0;
    }

    .nav {
        background-color: #333;
        overflow: hidden;
    }

    .nav a {
        float: left;
        display: block;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    .nav a:hover {
        background-color: #ddd;
        color: black;
    }
</style>

<div class="nav">
    <a href="/">Home</a>
    <a href="/profile">My Profile</a>
</div>

<div class="profile">
    <h1>My Profile</h1>
    <div class="profile-info">
        <h2>Profile Information</h2>
        <p><strong>Name:</strong> {profile.name}</p>
        <p><strong>Email:</strong> {profile.email}</p>
        <p><strong>Phone:</strong> {profile.phone}</p>
        <p><strong>Address:</strong> {profile.address}</p>
    </div>
</div>