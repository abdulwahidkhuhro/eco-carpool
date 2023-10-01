<%@page import="com.connect.ecocarpool.entities.User"%>
<%@page import="com.connect.ecocarpool.entities.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.connect.ecocarpool.entities.Message"%>
<%@page import="com.connect.ecocarpool.entities.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/style.css">
<jsp:include page="links.jsp" />

</head>
<body>



	<div class="home-container">
		<div class="user-navbar">
			<div class="logo-container">
				<div class="logo">
					<span class="logo-prefix">Eco</span><span class="logo-suffix">Carpool</span>
				</div>
			</div>
			<div class="user-details">
				<%
				User user = (User) session.getAttribute("currentUser");
				System.out.println("userHomePage customer : " + user);
				%>
				<div class="user-name"><%=user.getUsername()%></div>
				<a href="logout" class="logout-btn" class="logout">Log Out</a>
			</div>
		</div>
		<div class="user-home-body">

			<div class="user-home-tabs">
				<div class="user-home-tabs-btns">
					<a class="user-home-tab-btn-selected"
						href="customer?action=bookRide">Book a Ride</a> <a
						class="user-home-tab-btn" href="customer?action=myRides">My
						Rides</a>

				</div>
			</div>


			<div class="user-container" id="user-container-id">
				<div class="left-container">
					<form action="customer" method="POST">
						<%
						Message message = (Message) session.getAttribute("message");
						if (message != null) {
						%>
						<div class="<%=message.getCssClass()%>">
							<span><%=message.getContent()%></span>
						</div>
						<%
						session.removeAttribute("message");
						}
						%>
						<div class="field" style="display: none;">
							<div class="field-title">Full Name</div>
							<input type="text" class="field-input-box"
								value="<%=user.getUsername()%>" name="username">
						</div>
						<div class="field">
							<div class="field-title">Full Name</div>
							<input type="text" class="field-input-box"
								value="<%=user.getFullName()%>" disabled="disabled">
						</div>
						<div class="field">
							<div class="field-title">Pickup</div>
							<input type="text" class="field-input-box" placeholder="location"
								name="pickup">
						</div>
						<div class="field">
							<div class="field-title">Drop Off</div>
							<input type="text" class="field-input-box" placeholder="location"
								name="dropoff">
						</div>
						<div class="field">
							<div class="field-title">Date</div>
							<input type="date" class="field-input-box" placeholder="location"
								name="date">
						</div>
						<div class="field">
							<div class="field-title">Time</div>
							<input type="time" class="field-input-box" name="time">
						</div>

						<div class="field">
							<div class="field-title">Car Type</div>
							<ul class="car-type-menu">
								<li class="car-type-item"><img alt=""
									src="./icons/go-saver.png"><span class="service-name">Go
										Saver</span><span class="capacity">4 people</span></li>

								<li class="car-type-item"><img alt=""
									src="./icons/go-min.png"><span class="service-name">Go
										Min</span><span class="capacity">4 people</span></li>
								<li class="car-type-item"><img alt="" src="./icons/go.png"><span
									class="service-name">Go </span><span class="capacity">4
										people</span></li>
								<li class="car-type-item"><img alt=""
									src="./icons/go-premium.png"><span class="service-name">Go
										Premium</span><span class="capacity">4 people</span></li>
								<li class="car-type-item"><img alt=""
									src="./icons/bike.png"><span class="service-name">Bike
								</span><span class="capacity">1 people</span></li>
								<li class="car-type-item"><img alt=""
									src="./icons/riksha.png"><span class="service-name">Riksha
								</span><span class="capacity">3 people</span></li>


							</ul>
						</div>

						<div class="field" style="margin-top: 20px;">
							<div class="field-title">Select Car You Want:</div>

							<select name="selected-car" id="cars">
								<option value="Selected">Selected</option>
								<option value="Go Saver">Go Saver</option>
								<option value="Go Min">Go Min</option>
								<option value="v">Go</option>
								<option value="Go Premium">Go Premium</option>
								<option value="Bike">Bike</option>
								<option value="Riksha">Riksha</option>
							</select>
						</div>


						<div class="field">
							<input type="submit" value="Save" class="register-btn">
						</div>

					</form>





				</div>
				<div class="right-container" id="map">
				<!--	<div id="map"></div>
					  	<img alt="World map" src="./img/world-map.jpg"> -->
				</div>






			</div>
			<div class="user-home-footer">footer</div>
		</div>
	</div>


	<script>
				    
				    console.log("script in user home")
      var address = document.getElementById("address");
      var destAddress = document.getElementById("destAddress");
      var distanceEleme = document.getElementById("distance");

      const posToAddress = (loc) =>
        new Promise((resolve, reject) => {
          var geocoder = new google.maps.Geocoder();

          geocoder.geocode({ location: loc }, function (results, status) {
            if (status === "OK") {
              if (results[0]) {
                resolve(results[0].formatted_address);
              } else {
                reject("No results found");
              }
            } else {
              reject("Geocoder failed due to: " + status);
            }
          });
        });

      function initMap() {
        var map = new google.maps.Map(document.getElementById("map"), {
          zoom: 14,
          center: { lat: 4.164846975145926, lng: 73.52581474609373 },
        });

        var marker1 = new google.maps.Marker({
          position: { lat: 4.164846975145926, lng: 73.525814746093 },
          map: map,
          draggable: true,
        });

        var marker2 = new google.maps.Marker({
          position: { lat: 4.164846975145926, lng: 73.525814746093 },
          map: map,
          draggable: true,
        });

        var distance = google.maps.geometry.spherical.computeDistanceBetween(
          marker1.getPosition(),
          marker2.getPosition()
        );

        var infowindow = new google.maps.InfoWindow({
          content:
            "Distance: " +
            distance.toFixed(2) +
            " meters<br>Start: " +
            marker1.getPosition() +
            "<br>End: " +
            marker2.getPosition(),
        });

        infowindow.open(map, marker1);

        google.maps.event.addListener(marker1, "dragend", async function () {
          infowindow.close();
          distance = google.maps.geometry.spherical.computeDistanceBetween(
            marker1.getPosition(),
            marker2.getPosition()
          );

          address.value = await posToAddress({
            lat: marker1.getPosition().lat(),
            lng: marker1.getPosition().lng(),
          });
          destAddress.value = await posToAddress({
            lat: marker2.getPosition().lat(),
            lng: marker2.getPosition().lng(),
          });

          distanceEleme.value = distance;

          infowindow.setContent(
            "Distance: " +
              distance.toFixed(2) +
              " meters<br>Start: " +
              marker1.getPosition() +
              "<br>End: " +
              marker2.getPosition()
          );
          infowindow.open(map, marker1);
        });

        google.maps.event.addListener(marker2, "dragend", async function () {
          infowindow.close();
          distance = google.maps.geometry.spherical.computeDistanceBetween(
            marker1.getPosition(),
            marker2.getPosition()
          );

          address.value = await posToAddress({
            lat: marker1.getPosition().lat(),
            lng: marker1.getPosition().lng(),
          });
          destAddress.value = await posToAddress({
            lat: marker2.getPosition().lat(),
            lng: marker2.getPosition().lng(),
          });

          distanceEleme.value = distance;

          infowindow.setContent(
            "Distance: " +
              distance.toFixed(2) +
              " meters<br>Start: " +
              marker1.getPosition() +
              "<br>End: " +
              marker2.getPosition()
          );
          infowindow.open(map, marker1);
        });
      }
    </script>
	<script
		src="https://maps.googleapis.com/maps/api/js?libraries=geometry&key=AIzaSyDdp7PgVbhs7cnIvLgNq1HKWtNgmYaHtfI&callback=initMap"></script>


</body>
</html>