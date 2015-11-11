package controllers;

import models.*;
import play.data.Form;
import play.mvc.*;
import utility.FacebookWrapper;
import views.html.session;

import java.util.List;

public class SessionController extends Controller {

    public static Result createSession(Long userId) {
        Session session = Form.form(Session.class).bindFromRequest().get();
        session.hostId = userId;
        User user = User.find.byId(userId);
        session.joinedUsers.add(user); //only add record to session_user_joined once
        session.save();
        user.update();
        return redirect("/session/" + session.id + "/" + user.id);
    }

    public static Result showSession(Long sessionId, Long userId) {
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);
        return ok(session.render(s, u));
    }

    public static Result inviteUser(Long sessionId, String userName) {
        System.out.println("Inviting user " + userName + " to session " + sessionId);
        Session s = Session.find.byId(sessionId);
        List<User> users = User.find.where().eq("name", userName).findList();
        if(users.isEmpty()) {
            return badRequest();
        }
        User u = users.get(0);
        FacebookWrapper.inviteFriend(u.facebookId);
        s.unjoinedUsers.add(u);
        s.update();
        u.update();
        return ok();
    }

    public static Result leaveSession(Long sessionId, Long userId) {
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);
        s.joinedUsers.remove(u);
        s.unjoinedUsers.add(u);
        s.update();
        u.update();
        return redirect("/user/" + u.id);
    }

}
