<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Add POST</title>
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
		<style>
		    p {
                margin-left : 80px;
                text-align:center"
            }

            a:link, a:visited {
                color: yellow;
                text-decoration: none;
            }

            a:link:active, a:hover {
                color: green;
            }
		</style>
	</head>
	<body>	
	<div class="container">
		<h2 >Enter Details for new post : </h2>
		<form method="POST" action="addPost">
			<fieldset class="form-group">
				<label>Title</label>
				<input name="posttitle" type="text"class="form-control" maxlength="255" required = "required"/> <BR />
			</fieldset>
			<fieldset class="form-group">
				<label>Description</label>
				<input name="description" type="text" class="form-control" maxlength="255" required = "required"/> <BR />
			</fieldset>
			<input type="hidden" name=userid value= ${userid} />
			
			<input name="add" type="submit" class="btn btn-success"
			    style = "float:right; margin-right: 5%; background-color: yellow; color: blue" value="Submit" />
			    
			 <p><a class ="btn btn-success" href="userposts?userid=${userid}&usertypeid=2"  style = "float:right; margin-right: 5%;" >Back</a></p>
		</form>
	</div>
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</body>
</html>	