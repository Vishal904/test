<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
            <div class="container">
                <table class = "table table-striped" style = " border-bottom: 2px solid grey; border-top: 2px solid grey">
                    <thead>
                        <tr>
							<th>Airlines</th>
							<th>Departure City</th>
							<th>Arrival City</th>
							<th>Departure Time</th>
							<th>Select Flight</th>
						</tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${flights}" var="flight">
							<tr>
								<td>${flight.operatingAirlines}</td>
								<td>${flight.departureCity}</td>
								<td>${flight.arrivalCity}</td>
								<td>${flight.estimatedDepartureTime}</td>
								<td><a class = "btn btn-success" href="showCompleteReservation?flightId=${flight.id}" 
									style = "padding : 2px 2px; margin-bottom: 10px">Select</a></td>
							</tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty flights}">
                    <h2 align = "center" style="color:red;">NO FLIGHT EXISTS </h2>
                </c:if>
            </div>
            <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
            <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
     </body>
</html>