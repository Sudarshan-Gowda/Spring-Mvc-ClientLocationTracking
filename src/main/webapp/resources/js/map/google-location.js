// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see the error "The Geolocation service
// failed.", it means you probably did not give permission for the browser to
// locate you.
var map, infoWindow;

var lattTest, langTest;

function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : -34.397,
			lng : 150.644
		},
		zoom : 6
	});
	infoWindow = new google.maps.InfoWindow;

	// Try HTML5 geolocation.
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			lattTest = position.coords.latitude;
			langTest = position.coords.longitude;

			$.ajax({
				url : '/Spring-Mvc-ClientLocationTracking/google/loaction-track',
				contentType : "application/json; charset=utf-8",
				data : {
					'lattitude' : position.coords.latitude,
					'longitude' : position.coords.longitude,

				},
				type : 'GET',
				cache : false,
				success : function(result) {
					$("body").html(result);
				}
			});

			var pos = {
				lat : position.coords.latitude,
				lng : position.coords.longitude
			};

			infoWindow.setPosition(pos);
			infoWindow.setContent('Location found.');
			infoWindow.open(map);
			map.setCenter(pos);
		}, function() {
			handleLocationError(true, infoWindow, map.getCenter());
		});
	} else {
		// Browser doesn't support Geolocation
		handleLocationError(false, infoWindow, map.getCenter());
	}
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow
			.setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
					: 'Error: Your browser doesn\'t support geolocation.');
	infoWindow.open(map);
}
