function initSessionMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 40.713, lng: -74.006},
        zoom: 11
    });

    // Try HTML5 geolocation.
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			// Current user position
			var pos = {
			    lat: position.coords.latitude,
			    lng: position.coords.longitude
			};

			// Set map center to this position
			map.setCenter(pos);

			// Place a marker for the user
		    var markers = [];
		    markers.push(new google.maps.Marker({
		      position: pos,
		      map: map
		    }));

		    // Update user current position
		    updateLocation(userId, pos.lat, pos.lng);

		    // Place a marker at the location of each other joined user
		    joinedLocations.forEach(function(location) {
		    	markers.push(new google.maps.Marker({
		    		position: location,
		    		map: map
		    	}));
		    });

		    // Place a marker at the average location
		    var sumLocations = joinedLocations.reduce(function (a,b) {
		    	return {lat: a.lat + b.lat, lng: a.lng + b.lng};
		    });
		    var avgLocation = {
		    	lat: sumLocations.lat / joinedLocations.length,
		    	lng: sumLocations.lng / joinedLocations.length
		    };
		    var centerMarker = new google.maps.Marker({
		    	position: avgLocation,
		    	map: map,
		    	icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
		    });

		    // Actually set map center to midpoint
		    map.setCenter(avgLocation);

		    // Fit all markers on map
		    var bounds = new google.maps.LatLngBounds();
		    for(i = 0; i < markers.length; i++) bounds.extend(markers[i].getPosition());

		    map.fitBounds(bounds);

		});
	} else {
	// Browser doesn't support Geolocation

	}
}