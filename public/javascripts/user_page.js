function joinSession(sessionId, userId) {
    var url = window.location.origin + "/joinSession/" + sessionId + "/" + userId;
    window.location.href = url;
}

function showSession(sessionId, userId) {
    var url = window.location.origin + "/session/" + sessionId + "/" + userId;
    window.location.href = url;
}

function createSession(userId) {
    var name = $("#sessionName").val();
	if(!name) {
		alert("Session name cannot be empty!");
		return;
	}
    var url = window.location.origin + "/newSession/" + userId + "," + name;
    window.location.href = url;
}

function updateLocation(userId) {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			//Current User position
			var pos = {
				lat: position.coords.latitude,
				lng: position.coords.longitude
			};
			//Post to server
			var url = window.location.origin + "/updateLocation/" + userId + "/" + pos.lat + "/" + pos.lng;
			$.ajax({
				url: url,
				type: "POST"
			});
		});
	}
}