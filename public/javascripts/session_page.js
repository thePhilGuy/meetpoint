function inviteUser(sessionId) {
    var userId = $("input[name='userId']").val();
    var url = window.location.origin + "/inviteUser/" + sessionId + "/" + userId;
    $.ajax({
        url: url,
        type: "POST"
    });
}