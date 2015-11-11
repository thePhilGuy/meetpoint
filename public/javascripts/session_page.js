function inviteUser(sessionId, userName) {
    var url = window.location.origin + "/inviteUser/" + sessionId + "/" + userName;
    $.ajax({
        url: url,
        type: "POST"
    });
}