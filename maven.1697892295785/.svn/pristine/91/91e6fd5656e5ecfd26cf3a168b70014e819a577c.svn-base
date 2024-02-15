
<html>
<head>
    <title>404 - Page Not Found</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
           
            background-size: cover;
        }
        h1 {
            font-size: 2em;
            color: #FF0000;
        }
        p {
            font-size: 1.5em;
        }
        img {
            max-width: 100%;
            display: block;
            margin: 0 auto;
        }
        #timer {
            font-size: 1.5em;
            color: #333;
        }
    </style>
</head>
<body>
    <h1>404 - Page Not Found</h1>
    <p>Sorry, the page you are looking for does not exist.</p>
    <img src="https://media.tenor.com/5qtcif_1Xi8AAAAC/sad-doraemon.gif" alt="Find Out GIF - Find Out Lost GIFs">
    <p id="timer">Redirecting in <span id="countdown">10</span> seconds...</p>

    <script>
        // JavaScript Countdown Timer
        const countdownElement = document.getElementById('countdown');
        let countdown = 10;
        const timer = setInterval(() => {
            countdown--;
            countdownElement.textContent = countdown;
            if (countdown <= 0) {
                clearInterval(timer);
                window.location.href = '/home'; // Replace with your desired URL
            }
        }, 1000);
    </script>
</body>
</html>