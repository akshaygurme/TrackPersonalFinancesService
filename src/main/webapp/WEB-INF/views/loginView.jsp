<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <div class="container">
        <h1>Welcome to Login page with Spring Boot & JSP ${name}!</h1>
        <p>${message}</p>
        <form method="post">
            <label for="username">Username : </label>
            <input type="text" class="form-control" id="username" name="username" required><br>

            <label for="password" >Password : </label>
            <input type="password" class="form-control" id="password" name="password" required><br>

            <button type="submit" class="btn btn-success">Login</button>
        </form>
    </div>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>