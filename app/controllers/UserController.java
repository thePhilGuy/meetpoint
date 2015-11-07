package controllers;

import models.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import utility.FacebookWrapper;
import views.html.*;
import java.util.*;

public class UserController extends Controller {

    public static Result createUser(String facebookId) {
        User user = new User();
        user.facebookId = facebookId;
        user.isLoggedIn = true;
        user.save();
        return redirect("/user/" + user.id);
    }

    public static Result showUser(Long userId) {
        User u = User.find.byId(userId);
        //User userInfo = FacebookWrapper.getUserInfo();
        System.out.println(u.facebookId);
        System.out.println(u.name);
        System.out.println(u.joinedSessions.size());
        return ok(user.render(u));
    }

    public static Result joinSession(Long userId, Long sessionId) {
        User u = User.find.byId(userId);
        Session s = Session.find.byId(sessionId);
        u.joinedSessions.add(s);
        u.unjoinedSessions.remove(s);
        s.joinedUsers.add(u);
        s.unjoinedUsers.remove(u);
        u.update();
        s.update();
        return redirect("/session/" + sessionId);
    }
}
