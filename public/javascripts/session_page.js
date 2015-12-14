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
    console.log("userId: " + userId +  ", latitude: " + latitude + ", longitude: " + longitude);
    latitude += Math.random()*0.005;
    longitude += Math.random()*0.005;
	var url = window.location.origin + "/updateLocation/" + userId + "/" + latitude + "/" + longitude;
	$.ajax({
		url: url,
		type: "POST"
	});
}

function changeMeetType(sessionId) {
    var type = $("#locationType").val();
    var url = window.location.origin + "/updateMeetType/" + sessionId + "/" + type;
    $.ajax({
        url: url,
        type: "POST"
    });
}

$( document ).ready(function() {
    $("#locationType").val(meetType);
});
