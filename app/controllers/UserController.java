package controllers;

import models.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import utility.FacebookWrapper;
import views.html.*;
import java.util.*;

public class UserController extends Controller {

    public static Result createUser() {
        User user = Form.form(User.class).bindFromRequest().get();
        user.save();
        return redirect("/user/" + user.id);
    }

    public static Result showUser(Long userId) {
        User u = User.find.byId(userId);
        User userInfo = FacebookWrapper.getUserInfo();
        return ok(user.render(u));
    }

    public static Result joinSession(Long userId, Long sessionId) {
        User u = User.find.byId(userId);
        Session s = Session.find.byId(sessionId);
        u.joinedSessions.add(sessionId);
        u.unjoinedSessions.remove(sessionId);
        s.joinedUsers.add(userId);
        s.unJoinedUsers.remove(userId);
        u.save();
        s.save();
        return redirect("/session/" + sessionId);
    }
}
