package controllers;

import models.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.session;
import java.util.*;

public class SessionController extends Controller {

    public static Result createSession(Long userId) {
        Session session = Form.form(Session.class).bindFromRequest().get();
        session.hostId = userId;
        session.save();
        return redirect("/session/" + session.id);
    }

    public static Result showSession(Long sessionId) {
        Session s = Session.find.byId(sessionId);
        return ok(session.render(s));
    }

    public static Result inviteUser(Long sessionId, Long userId) {
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);
        s.unJoinedUsers.add(userId);
        u.unjoinedSessions.add(sessionId);
        return ok();
    }
}
