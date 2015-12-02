function inviteUser(sessionId, userName) {
    console.log("javascript inviteUser called");
    var url = window.location.origin + "/inviteUser/" + sessionId + "/" + userName;
    $.ajax({
        url: url,
        type: "POST"
    });
    // window.location.href = window.location.origin + '/session/' + sessionId + '/' + userId;
    window.location.reload();
}

function updateLocation(userId, latitude, longitude) {
	console.log("javascript updateLocation called");
	var url = window.location.origin + "/updateLocation/" + userId + "/" + latitude + "/" + longitude;
	$.ajax({
		url: url,
		type: "POST"
	});
}