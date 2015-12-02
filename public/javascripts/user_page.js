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
    var url = window.location.origin + "/newSession/" + userId + "," + name;
    window.location.href = url;
}