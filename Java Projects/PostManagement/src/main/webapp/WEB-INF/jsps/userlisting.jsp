<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Yahoo!!</title>
        <!-- Bootstrap core CSS -->
        <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
            <div class="container">
                <H1>Welcome ${name}</H1>
                <a class = "btn btn-danger" href="showlogin"
                    style = "float : right; padding : 2px 2px; margin-bottom: 10px">Logout</a>
                <a class = "btn btn-success" href="profile?userid=1&usertypeid=1"
                    style = "float:right; margin-right : 20px; padding : 2px 2px; margin-bottom: 10px" >profile</a>
                <table class = "table table-striped" style = " border-bottom: 2px solid grey; border-top: 2px solid grey">
                    <thead>
                        <th>User Id</th>
                        <th>FirstName</th>
                        <th>LastName</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Profile</th>
                        <th>Posts</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.fname}</td>
                                <td>${user.lname}</td>
                                <td>${user.email}</td>
                                <td>${user.phone}</td>
                                <td><a class = "btn btn-success" href="profile?userid=${user.id}&usertypeid=1"
                                    style = "padding : 2px 2px; margin-bottom: 10px">Profile</a>
                                </td>
                                <td><a class = "btn btn-success" href="userposts?userid=${user.id}&usertypeid=1"
                                    style = "background-color: hotpink; padding : 2px 2px; margin-bottom: 10px">Posts</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty users}">
                    <h2 align = "center" style="color:red;">NO USER EXISTS </h2>
                </c:if>
            </div>
            <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
            <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
     </body>
</html>