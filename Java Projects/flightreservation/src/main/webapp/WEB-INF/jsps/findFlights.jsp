<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Find Flights</title>
		<style>
			body, html {
			  height: 100%;
			  margin: 0;
			}
			
			.bg {
			  background-image: url("images/searchflight.jpg");
			  height: 100%; 
			  background-position: center;
			  background-repeat: no-repeat;
			  background-size: cover;
			}
			div {
				position: absolute;
				margin: auto;
				top: 0;
				right: 0;
				bottom: 0;
				left: 0;
				width: 500px;
				height: 125px;
				background-color: blue;
				opacity: 0.9;
				border-radius : 10px;
			}
			input[type="text"]::placeholder {  
                /* Firefox, Chrome, Opera */ 
                text-align: center; 
            } 
			input[type="text"]:-ms-input-placeholder { 
                /* Internet Explorer 10-11 */ 
                text-align: center; 
            } 
			input[type="text"] {
				width : 20%;
				height : 23px;
				border : 2px solid;
				border-radius : 5px;
			}
			input[type="submit"] {
				width : 15%;
				height : 30px;
				border : 2px solid;
				border-radius : 5px;
				background-color : lightblue;
				margin-left : 5%;
			}
			</style>
	</head>
	<body class="bg">
	<div align="center">
		<h2>Find Flights:</h2>
		<form action="findFlights" method="post">
			<input type="text" name="from" placeholder = "From" />
			<input type="text" name="to" placeholder = "To"/>
			<input type="text" name="departureDate" placeholder = "Date"/>
			<input type="submit" value="search"/> 
	</form>
	</div>
</body>
</html>