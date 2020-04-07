<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Yahoo!!</title>
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
            var userid = '<%= session.getAttribute("usertypeid") %>';
            if(userid== 1) {
                document.getElementById("#02a").innerHTML = "Back";
                document.getElementById("#02a").href = "getusers.do";
            }
            else {
                document.getElementById("#02a").innerHTML = "Logout";
                document.getElementById("#02a").href = "logout.do";
            }
        }
    </script>
    <body onload = "checkUserTypeid()">
        <%   String display = "";
             int usertypeid = (Integer) session.getAttribute("usertypeid");
             int userid = 0;
         %>
         <% if(usertypeid==1){
             display = "none";
             userid = Integer.parseInt(request.getParameter("userid"));
         }
         else {%>

         <% } %>
        <div class="container">
            <H1>Welcome ${firstname}</H1>
            <a id = "#02a" class = "btn btn-danger" style = "float : right; padding : 2px 2px; margin-bottom: 10px" >Logout</a>
             <a id = "#03a" class = "btn btn-success" href="profile.do?userid=<%= session.getAttribute("userid") %>"
                style = "float:right; margin-right : 20px; padding : 2px 2px; margin-bottom: 10px; display : <%= display %>">Profile
             </a>
            <table class = "table table-striped" style = " border-bottom: 2px solid grey; border-top: 2px solid grey">
                <thead>
                    <th>Post Id</th>
                    <th>Post Title</th>
                    <th>Post Description</th>
                    <th>Date</th>
                    <th id = "#01td" style = "display : <%= display %>">Edit Post</th>
                    <th style = "display : <%= display %>">Delete</th>
                </thead>
                <tbody>
                    <c:forEach items="${userposts}" var="post">
                        <tr>
                            <td>${post.id}</td>
                            <td>${post.posttitle}</td>
                            <td>${post.description}</td>
                            <td>${fn:substring(post.date, 0, 10)}</td>
                            <td id = "#05td1" style = "display : <%= display %>">
                                <a id = "#05a" class = "btn btn-success"
                                    href="editpost.do?postid=${post.id}&title=${post.posttitle}&description=${post.description}"
                                    style = "padding : 3px 10px; margin-bottom: 10px">Edit
                                </a>
                            </td>
                            <td id = "#06td1" style = "display : <%= display %>">
                                <a  id = "#06a" class = "btn btn-danger" href="deletepost.do?postid=${post.id}"
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
            <a  id = "#04a" class = "btn btn-success" href="addPost.do?userid='<%= session.getAttribute("userid") %>'"
                style = "float:right; margin-right: 5%; background-color: yellow; color: blue;display : <%= display %>">Add Post
            </a>
        </div>
        <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
     </body>
</html>