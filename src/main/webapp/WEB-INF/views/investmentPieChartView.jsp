<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Investment Pie Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        /* Center everything on the page */
        body, html {
            height: 100%;
            margin: 0;
            display: flex;
            justify-content: center; /* Horizontal center */
            align-items: center;     /* Vertical center */
            font-family: Arial, sans-serif;
        }

        /* Container for the pie chart */
        .chart-container {
            width: 80%;  /* Adjust width of the chart */
            max-width: 600px;  /* Maximum width */
            height: 80%; /* Adjust height */
            display: flex;
            justify-content: center;
            align-items: center;
        }

        canvas {
            width: 100%;  /* Fill the container's width */
            height: 100%; /* Fill the container's height */
        }
    </style>
</head>

<body>

    <div class="chart-container">
        <h2>Emergency Fund Distribution</h2>
        <canvas id="emergencyFundPieChart" width="500" height="500"></canvas>
    </div>
    <script>
        // Parse the investmentData JSON string into a JavaScript object
        const investmentData = JSON.parse('${investmentData}');

        // Extract the labels (keys) and values from the object
        const labels = Object.keys(investmentData);  // e.g., ["Bank FDs", "Invoice Discounting", ...]
        const values = Object.values(investmentData);  // e.g., [10031.0, 10041.0, ...]

        // Create the pie chart using Chart.js
        const ctx = document.getElementById('emergencyFundPieChart').getContext('2d');
        new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels,
                datasets: [{
                    data: values,
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4CAF50'] // Colors for the pie slices
                }]
            },
            options: {
                responsive: false,
                plugins: {
                    legend: {
                        position: 'top'
                    }
                }
            }
        });
    </script>

</body>
</html>
