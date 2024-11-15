
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
    <title>Login Page</title>
</head>
<body>
    <div class="container">
        <h1>Add TODO</h1>
        <form:form method="post" modelAttribute="todo">
            <label for="description">Description : </label>
            <form:input type="text" path="description" required="required"/>
            <form:errors path="description"/>
            <br>
            <form:input type = "hidden" path="id"/>
            <form:input type="hidden" path="done"/>
            <button type="submit" class="btn btn-success">Add ToDo</button>
        </form:form>
    </div>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>