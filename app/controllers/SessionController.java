package controllers;

import models.*;
import play.data.Form;
import play.mvc.*;
import views.html.session;

public class SessionController extends Controller {

    public static Result createSession(Long userId) {
        Session session = Form.form(Session.class).bindFromRequest().get();
        session.hostId = userId;
        User user = User.find.byId(userId);
        session.joinedUsers.add(user);
        session.save();
        user.joinedSessions.add(session);
        System.out.println("Created session");
        user.printJoinedSessions();
        user.printUnjoinedSessions();
        //user.update();
        return redirect("/session/" + session.id + "/" + user.id);
    }

    public static Result showSession(Long sessionId, Long userId) {
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);
        System.out.println("About to render session:");
        u.printJoinedSessions();
        u.printUnjoinedSessions();
        return ok(session.render(s, u));
    }

    public static Result inviteUser(Long sessionId, Long userId) {
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);
        s.unjoinedUsers.add(u);
        u.unjoinedSessions.add(s);
        //s.update();
        //u.update();
        return ok();
    }

    public static Result leaveSession(Long sessionId, Long userId) {
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);
        s.joinedUsers.remove(u);
        s.unjoinedUsers.add(u);
        u.joinedSessions.remove(s);
        u.unjoinedSessions.add(s);
        s.update();
        u.update();
        return redirect("/user/" + u.id);
    }
}
