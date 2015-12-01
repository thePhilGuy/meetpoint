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
        FacebookWrapper.getUserName(u.facebookId, u.userAccessToken);
        return ok(user.render(u));
    }

    public static Result joinSession(Long sessionId, Long userId) {
        User u = User.find.byId(userId);
        Session s = Session.find.byId(sessionId);
        s.joinedUsers.add(u);
        s.unjoinedUsers.remove(u);
        u.update();
        s.update();
        return redirect("/session/" + sessionId + "/" + userId);
    }

}
