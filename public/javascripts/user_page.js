function joinSession(sessionId, userId) {
    console.log("join session");
    console.log(window.location);
    var url = window.location.origin + "/joinSession/" + sessionId + "/" + userId;
    window.location.href = url;
}

function showSession(sessionId, userId) {
    console.log("show session");
    var url = window.location.origin + "/session/" + sessionId + "/" + userId;
    window.location.href = url;
}