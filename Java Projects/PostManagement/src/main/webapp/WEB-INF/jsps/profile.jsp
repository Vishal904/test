<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>	
	<head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<style>
		#profile-image1 {
			cursor: pointer;  
			width: 100px;
			height: 100px;
			border:2px solid #03b1ce;
		}
		th, td {
			padding: 15px;
			text-align: left;
		}
		table#t01 tr:nth-child(even) {
			background-color: #eee;
		}
		table#t01 tr:nth-child(odd) {
			background-color: #fff;
		}
		#div01 {
			max-width : 500px; 
			border : 1px solid #03b1ce; 
			padding : 40px ; 
			text-align : center; 
			background-color : lightblue
		}
	</style>
	<script>
	    function checkUserTypeid() {
	        var usertypeid = ${usertypeid};
	        if(usertypeid== 1) {
	            document.getElementById("#01a").href = "userlisting";
	        }
	        else {
	            document.getElementById("#01a").href = "userposts?userid=${user.id}&usertypeid=2";
	        }
	    }
	</script>
	</head>
	<body onload = "checkUserTypeid()">
		<div align = "center" style = "background-color : #c0f0ed">
			<div id = "div01" style = "max-width : 500px; border : 1px solid #03b1ce; padding : 40px ; text-align : center; background-color : lightblue">
				<div class="col-sm-6">
					<div  align="center"> <img alt="User Pic" src="https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg" id="profile-image1" class="img-circle img-responsive"> 
						<div style="color:#999;" >click here to change profile image</div>        
					</div><BR />
				</div>
			<div><br>
			<div>
			    <div class="col-sm-6" align = "center">
                    <h4 style="color:#00b1b1;">${user.fname} &nbsp;${user.lname} </h4></span>
                </div>
				<div align = "center">
					<table id = "t01">
						<tr>
							<td>Email :</td>
							<td>${user.email}</td>
						</tr>
						<tr>
							<td>Phone :</td>
							<td>${user.phone}</td>
						</tr>
						<tr>
							<td>Age :</td>
							<td>${user.age}</td>
						</tr>
						<tr>
							<td>Gender :</td>
							<td>${user.gender}</td>
						</tr>
						<tr>
                            <td>Address :</td>
                            <td>${address.address}</td>
                        </tr>
                        <tr>
                            <td>district :</td>
                            <td>${address.district}</td>
                        </tr>
                        <tr>
                            <td>city :</td>
                            <td>${address.city}</td>
                        </tr>
					<table><BR/>
				</div>
			</div>
			<a id = "#01a" class = "btn btn-success" href="userposts">Back</a><br>
		</div>
	</body>
</html>	




         