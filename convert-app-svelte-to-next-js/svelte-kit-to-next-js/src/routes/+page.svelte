<script lang="ts">
    // Mock data for demonstration purposes
    const topSpenders = [
        { name: 'John Doe', amount: 1200 },
        { name: 'Jane Smith', amount: 1100 },
        { name: 'Alice Johnson', amount: 1050 },
        { name: 'Bob Brown', amount: 1000 },
        { name: 'Charlie Davis', amount: 950 },
        { name: 'Diana Evans', amount: 900 },
        { name: 'Frank Green', amount: 850 },
        { name: 'Grace Harris', amount: 800 },
        { name: 'Henry Jackson', amount: 750 },
        { name: 'Ivy King', amount: 700 }
    ];

    const bestSellingProducts = [
        { name: 'Product A', price: 50, soldCount: 200 },
        { name: 'Product B', price: 30, soldCount: 180 },
        { name: 'Product C', price: 20, soldCount: 160 },
        { name: 'Product D', price: 40, soldCount: 150 },
        { name: 'Product E', price: 25, soldCount: 140 },
        { name: 'Product F', price: 35, soldCount: 130 },
        { name: 'Product G', price: 45, soldCount: 120 },
        { name: 'Product H', price: 55, soldCount: 110 },
        { name: 'Product I', price: 60, soldCount: 100 },
        { name: 'Product J', price: 70, soldCount: 90 }
    ];
</script>

<style>
    .dashboard {
        font-family: Arial, sans-serif;
        padding: 20px;
    }

    h1 {
        text-align: center;
        color: #333;
    }

    .section {
        margin-bottom: 40px;
    }

    .section h2 {
        color: #555;
        border-bottom: 2px solid #ddd;
        padding-bottom: 10px;
        margin-bottom: 20px;
    }

    .table {
        width: 100%;
        border-collapse: collapse;
    }

    .table th, .table td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: left;
    }

    .table th {
        background-color: #f4f4f4;
    }

    .table tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .table tr:hover {
        background-color: #f1f1f1;
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

<div class="dashboard">
    <h1>Dashboard</h1>

    <div class="section">
        <h2>Top 10 Spenders of the Month</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Amount Spent</th>
                </tr>
            </thead>
            <tbody>
                {#each topSpenders as spender}
                    <tr>
                        <td>{spender.name}</td>
                        <td>${spender.amount}</td>
                    </tr>
                {/each}
            </tbody>
        </table>
    </div>

    <div class="section">
        <h2>Top 10 Best Selling Products</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Sold Count</th>
                </tr>
            </thead>
            <tbody>
                {#each bestSellingProducts as product}
                    <tr>
                        <td>{product.name}</td>
                        <td>${product.price}</td>
                        <td>{product.soldCount}</td>
                    </tr>
                {/each}
            </tbody>
        </table>
    </div>
</div>
