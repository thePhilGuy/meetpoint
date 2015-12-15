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
    getUserList(sessionId);
});

function getUserList(sessionId) {
    console.log("getUserList called");
    var url = window.location.origin + "/getCurrentUsers/" + sessionId;
    $.get( url, function( data ) {
        var joinedUsers = data.joinedUsersList;
        var unjoinedUsers = data.unjoinedUsersList;
        if(JSON.stringify(joinedUsers) != JSON.stringify(displayedJoinedUsers) ) {
            displayedJoinedUsers = joinedUsers;
            $("#joined_sessions").empty();
            for(var i=0; i<displayedJoinedUsers.length; i++) {
                $("#joined_sessions").append('<li class="list-group-item">' + joinedUsers[i] + '</li>');
            }
        }
        if(JSON.stringify(unjoinedUsers) != JSON.stringify(displayedUnjoinedUsers)) {
            displayedUnjoinedUsers = unjoinedUsers;
            $("#unjoined_sessions").empty();
            for(var i=0; i<displayedUnjoinedUsers.length; i++) {
                $("#unjoined_sessions").append('<li class="list-group-item">' + unjoinedUsers[i] + '</li>');
            }
        }
        setTimeout(function() { getUserList(sessionId); }, 10000);
    });
}