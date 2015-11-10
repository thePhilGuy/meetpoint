package controllers;

import models.*;
import play.data.Form;
import play.mvc.*;
import utility.FacebookWrapper;
import views.html.*;
import java.util.*;

public class UserController extends Controller {

    public static Result createUser(String facebookId, String accessToken) {
        User user = new User();
        user.facebookId = facebookId;
        user.name = FacebookWrapper.getUserName(facebookId, accessToken);
        user.userAccessToken = accessToken;
        user.isLoggedIn = true;
        user.friends = FacebookWrapper.getFriends(accessToken);
        user.save();
        return redirect("/user/" + user.id);
    }

    public static Result showUser(Long userId) {
        User u = User.find.byId(userId);
        // System.out.println("Before update friends.size: " + u.friends.size());
        u.friends = FacebookWrapper.getFriends(u.userAccessToken);
        // System.out.println("After update friends.size: " + u.friends.size());
        u.save();
        // u.update();
        return ok(user.render(u));
    }

    public static Result joinSession(Long sessionId, Long userId) {
        User u = User.find.byId(userId);
        Session s = Session.find.byId(sessionId);
        u.joinedSessions.add(s);
        u.unjoinedSessions.remove(s);
        s.joinedUsers.add(u);
        s.unjoinedUsers.remove(u);
        //u.update();
        //s.update();
        return redirect("/session/" + sessionId + "/" + userId);
    }

}
