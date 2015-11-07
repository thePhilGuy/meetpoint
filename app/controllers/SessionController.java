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
        session.joinedUsers.add(userId);
        session.save();
        User user = User.find.byId(userId);
        user.joinedSessions.add(session.id);
        user.save();
        return redirect("/session/" + session.id + "," + user.id);
    }

    public static Result showSession(Long sessionId, Long userId) {
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);
        return ok(session.render(s, u));
    }

    public static Result inviteUser(Long sessionId, Long userId) {
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);
        s.unJoinedUsers.add(userId);
        u.unjoinedSessions.add(sessionId);
        s.save();
        u.save();
        return ok();
    }

    public static Result leaveSession(Long sessionId, Long userId) {
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);

        s.joinedUsers.remove(userId);
        s.unJoinedUsers.add(userId);
        s.save();

        u.joinedSessions.remove(sessionId);
        u.unjoinedSessions.add(sessionId);
        u.save();

        return redirect("/user/" + u.id);
    }
}
