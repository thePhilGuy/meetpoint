package controllers;

import models.*;
import play.mvc.*;
import utility.FacebookWrapper;
import views.html.*;

import java.util.List;

public class UserController extends Controller {

    public static Result createUser(String facebookId, String accessToken) {
        User user = new User();
        user.facebookId = facebookId;
        user.name = FacebookWrapper.getUserName(accessToken);
        user.userAccessToken = accessToken;
        user.isLoggedIn = true;
        user.friends = FacebookWrapper.getFriends(accessToken);
        user.save();
        return redirect("/user/" + user.id);
    }

    public static Result showUser(Long userId) {
        List<User> users = User.find.where().eq("id", userId).findList();
        if (users.size() == 0) {
            return badRequest();
        }
        User u = users.get(0);
        FacebookWrapper.getUserName(u.userAccessToken);
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

    public static Result updateLocation(Long userId, double lat, double lng) {
        User u = User.find.byId(userId);
        u.latitude = lat;
        u.longitude = lng;
        u.update();
        return ok();
    }

}
