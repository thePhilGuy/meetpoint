function inviteUser(sessionId, userName) {
    console.log("javascript inviteUser called");
    var url = window.location.origin + "/inviteUser/" + sessionId + "/" + userName;
    $.ajax({
        url: url,
        type: "POST"
    });
}