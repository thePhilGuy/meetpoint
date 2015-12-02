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
		    var marker = new google.maps.Marker({
		      position: pos,
		      map: map
		    });

		    // Update user current position
		    updateLocation(userId, pos.lat, pos.lng);

		    // Place a marker at the location of each other joined user
		    joinedLocations.forEach(function(location) {
		    	var marker = new google.maps.Marker({
		    		position: location,
		    		map: map
		    	});
		    });

		});
	} else {
	// Browser doesn't support Geolocation

	}
}