var map;
var avgLocation;
var markers = [];
var bounds;
var currentType = "";
var currentjoinedLocations = "";

function initSessionMap() {

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			// Update user current position
			updateLocation(userId, position.coords.latitude, position.coords.longitude);

			/*
			// Place a marker for the user
		    var markers = [];
		    var userMarker = new google.maps.Marker({
		      position: pos,
		      map: map
		    });

		    Attach its info window
		    var userInfo = new google.maps.InfoWindow({
		    	content: "Me"
		    });
		    userMarker.addListener('click', function() {
		    	userInfo.open(map, userMarker);
		    })
		    markers.push(userMarker);
		    */
		});
	}

	map = new google.maps.Map(document.getElementById('map'), {
		center: {lat: 40.713, lng: -74.006},
		zoom: 11
	});
	drawMarkers();
}

function drawMarkers() {
	var url = window.location.origin + "/meetPoints/" + sessionId;
	$.get( url, function( data ) {
		var type = data.meetType;
		var joinedLocations = data.jointList;
		if(type != currentType || JSON.stringify(joinedLocations) != JSON.stringify(currentjoinedLocations)) {
			currentType = type;
			currentjoinedLocations = joinedLocations;
			// Place a marker at the location of each other joined user
			clearMarker();
			bounds = new google.maps.LatLngBounds();
			map.fitBounds(bounds);
			markers = [];
			joinedLocations.forEach(function(location) {
				var locationPos = {
					lat: location.lat,
					lng: location.lng
				};
				var locationMarker = new google.maps.Marker({
					position: locationPos,
					map: map
				});
				markers.push(locationMarker);
			});

			// Place a marker at the average location
			var sumLocations = joinedLocations.reduce(function (a,b) {
				return {lat: a.lat + b.lat, lng: a.lng + b.lng};
			});
			avgLocation = {
				lat: sumLocations.lat / joinedLocations.length,
				lng: sumLocations.lng / joinedLocations.length
			};
			var centerMarker = new google.maps.Marker({
				position: avgLocation,
				map: map,
				icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
			});
			markers.push(centerMarker);
			// Actually set map center to midpoint
			map.setCenter(avgLocation);

			// Fit all markers on map
			for(var i = 0; i < markers.length; i++) {
				bounds.extend(markers[i].getPosition());
			}
			map.fitBounds(bounds);
			console.log(type);
			if(type && type != 'default') {
				var request = {
					location: avgLocation,
					radius: 300,
					types: [type]
				};
				var service = new google.maps.places.PlacesService(map);
				service.nearbySearch(request, showPlaces);
			}
		}
		setTimeout(drawMarkers, 3000);
	});
}

function showPlaces(results, status) {
	if (status == google.maps.places.PlacesServiceStatus.OK) {
		for (var i = 0, place; place = results[i]; i++) {
			var image = {
				url: place.icon,
				size: new google.maps.Size(70, 70),
				origin: new google.maps.Point(0, 0),
				anchor: new google.maps.Point(17, 34),
				scaledSize: new google.maps.Size(25, 25)
			};

			var marker = new google.maps.Marker({
				map: map,
				icon: image,
				title: place.name,
				position: place.geometry.location
			});
			markers.push(marker);
			bounds.extend(place.geometry.location);
		}
		map.fitBounds(bounds);
	}
}

function clearMarker() {
	for(var i=0; i<markers.length; i++) {
		markers[i].setMap(null);
	}
}