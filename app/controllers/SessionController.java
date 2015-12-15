package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonArray;
import models.*;
import play.api.libs.json.JsObject;
import play.libs.Json;
import play.mvc.*;
import utility.FacebookWrapper;
import views.html.session;

import java.util.ArrayList;
import java.util.List;

public class SessionController extends Controller {

    static class Position {
        public double lat;
        public double lng;
        public Position(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }

    public static Result createSession(Long userId, String name) {
        if(!session("user_id").equals(userId.toString())) return forbidden("Cannot create session for another user.");
        System.out.println(userId);
        System.out.println(name);
        List<Session> sessions = Session.find.where().eq("name", name).findList();
        if(sessions.size() > 0) {
            return status(409, "Session already existed");
        }
        Session session = new Session();
        session.hostId = userId;
        session.name = name;
        session.meetType = "";
        User user = User.find.byId(userId);
        session.joinedUsers.add(user);
        session.save();
        user.update();
        return redirect("/session/" + session.id + "/" + user.id);
    }

    public static Result showSession(Long sessionId, Long userId) {
        if(!session("user_id").equals(userId.toString())) return forbidden("Cannot view session as another user.");
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);
        User h = User.find.byId(s.hostId);
        u.friends = FacebookWrapper.getFriends(u.userAccessToken);
        u.update();
        return ok(session.render(s, u, h));
    }

    public static Result inviteUser(Long sessionId, String userName) {
        System.out.println("Inviting user " + userName + " to session " + sessionId);
        Session s = Session.find.byId(sessionId);
        if(!session("user_id").equals(s.hostId.toString())) return forbidden("Only host can invite users.");
        List<User> users = User.find.where().eq("name", userName).findList();
        if(users.size() == 0) {
            return badRequest();
        }
        User u = users.get(0);
        if(!s.unjoinedUsers.contains(u) && !s.joinedUsers.contains(u)) {
            s.unjoinedUsers.add(u);
        }
        s.update();
        u.update();
        return ok();
    }

    public static Result updateMeetType(Long sessionId, String meetType) {
        Session s = Session.find.byId(sessionId);
        if(!session("user_id").equals(s.hostId.toString())) return forbidden("Only host can update meet type.");
        s.meetType = meetType;
        s.update();
        return ok();
    }

    public static Result getMeetPoints(Long sessionId) {
        Session s = Session.find.byId(sessionId);
        if(s == null) {
            return badRequest();
        }
        ObjectNode result = Json.newObject();
        List<Position> list = new ArrayList<Position>();
        for(User u : s.joinedUsers) {
            list.add(new Position(u.latitude, u.longitude));
        }
        result.put("jointList", Json.toJson(list));
        result.put("meetType", s.meetType);
        return ok(result);
    }

    public static Result leaveSession(Long sessionId, Long userId) {
        if(!session("user_id").equals(userId.toString())) return forbidden("Cannot leave session for another user.");
        Session s = Session.find.byId(sessionId);
        User u = User.find.byId(userId);
        s.joinedUsers.remove(u);
        s.unjoinedUsers.add(u);
        u.update();
        s.update();
        return redirect("/user/" + u.id);
    }

    public static Result getCurrentUsers(Long sessionId) {
        Session s = Session.find.byId(sessionId);
        if(s == null) {
            return badRequest();
        }
        ObjectNode result = Json.newObject();
        List<String> joinedUserNames = new ArrayList<String>();
        List<String> unjoinedUserNames = new ArrayList<String>();
        for(User u : s.joinedUsers) {
            joinedUserNames.add(u.name);
        }
        for(User u: s.unjoinedUsers) {
            unjoinedUserNames.add(u.name);
        }
        result.put("joinedUsersList", Json.toJson(joinedUserNames));
        result.put("unjoinedUsersList", Json.toJson(unjoinedUserNames));
        return ok(result);
    }
}
