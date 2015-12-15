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
        session("user_id", user.id.toString());
        return redirect("/user/" + user.id);
    }

    public static Result showUser(Long userId) {
        if(!session("user_id").equals(userId.toString())) return forbidden();
        List<User> users = User.find.where().eq("id", userId).findList();
        if (users.size() == 0) {
            return badRequest();
        }
        User u = users.get(0);
        FacebookWrapper.getUserName(u.userAccessToken);
        return ok(user.render(u));
    }

    public static Result joinSession(Long sessionId, Long userId) {
        if(!session("user_id").equals(userId.toString())) return forbidden();
        User u = User.find.byId(userId);
        Session s = Session.find.byId(sessionId);
        s.joinedUsers.add(u);
        s.unjoinedUsers.remove(u);
        u.update();
        s.update();
        return redirect("/session/" + sessionId + "/" + userId);
    }

    public static Result updateLocation(Long userId, double lat, double lng) {
        if(!session("user_id").equals(userId.toString())) return forbidden();
        User u = User.find.byId(userId);
        u.latitude = lat;
        u.longitude = lng;
        u.update();
        return ok();
    }

}
