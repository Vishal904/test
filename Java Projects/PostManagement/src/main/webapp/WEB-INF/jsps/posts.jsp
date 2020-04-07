<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Posts!!</title>
        <!-- Bootstrap core CSS -->
        <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <style>
            td  {
                min-width: 100px;
            }
        </style>
    </head>
    <script>
        function checkUserTypeid() {
            var usertypeid = ${usertypeid};            
            if(usertypeid== 1) {
                document.getElementById("#02a").innerHTML = "Back";
                document.getElementById("#02a").href = "userlisting";
            }
            else {
                document.getElementById("#02a").innerHTML = "Logout";
                document.getElementById("#02a").href = "showlogin";
            }
        }
    </script>
    <body onload = "checkUserTypeid()">
        <div class="container">
            <H1>Welcome ${firstname}</H1>
            <a id = "#02a" class = "btn btn-danger" style = "float : right; padding : 2px 2px; margin-bottom: 10px" >Logout</a>
             <a id = "#03a" class = "btn btn-success" href="profile?userid=${userid}&usertypeid=2"
                style = "float:right; margin-right : 20px; padding : 2px 2px; margin-bottom: 10px; display : ${display} ">Profile
             </a>
            <table class = "table table-striped" style = " border-bottom: 2px solid grey; border-top: 2px solid grey">
                <thead>
                    <th>Post Id</th>
                    <th>Post Title</th>
                    <th>Post Description</th>
                    <th>Date</th>
                    <th id = "#01td" style = "display : ${display}">Edit Post</th>
                    <th style = "display : ${display} ">Delete</th>
                </thead>
                <tbody>
                    <c:forEach items="${userposts}" var="post">
                        <tr>
                            <td>${post.id}</td>
                            <td>${post.posttitle}</td>
                            <td>${post.description}</td>
                            <td>${fn:substring(post.date, 0, 10)}</td>
                            <td id = "#05td1" style = "display : ${display}">
                                <a id = "#05a" class = "btn btn-success"
                                    href="editpost?postid=${post.id}&title=${post.posttitle}&description=${post.description}"
                                    style = "padding : 3px 10px; margin-bottom: 10px">Edit
                                </a>
                            </td>
                            <td id = "#06td1" style = "display : ${display}">
                                <a  id = "#06a" class = "btn btn-danger" href="deletepost?postid=${post.id}&userid=${post.userid}"
                                    style = "background-color: hotpink; padding : 2px 2px; margin-bottom: 10px">Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${empty userposts}">
                <h2 align = "center" style="color:red;">NO POST EXISTS </h2>
            </c:if>
            <a  id = "#04a" class = "btn btn-success" href="newPost?userid=${userid}"
                style = "float:right; margin-right: 5%; background-color: yellow; color: blue;display : ${display}">Add Post
            </a>
        </div>
        <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
     </body>
</html>