function joinSession(sessionId, userId) {
    console.log("join session");
    console.log(window.location.hostname);
    var url = window.location.hostname + "/joinSession/" + sessionId + "/" + userId;
    window.location.href = url;
}

function showSession(sessionId, userId) {
    console.log("show session");
    var url = window.location.hostname + "/session/" + sessionId + "/" + userId;
    window.location.href = url;
}