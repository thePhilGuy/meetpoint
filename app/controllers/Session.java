package controllers;

import models.*;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;
import views.html.session;
import java.util.List;

public class Session extends Controller {

    public static Result createSession() {
        models.Session session = Form.form(models.Session.class).bindFromRequest().get();
        session.save();
        return redirect("/session/" + session.id);
    }

    public static Result showSession(Long sessionId) {
        List<models.User> users = getUsersBySession(sessionId);
        return ok(session.render(""));
    }

    public static List<models.User> getUsersBySession(Long sessionId) {
        List<models.User> users = new Model.Finder(String.class, models.User.class).all();
        return users;
    }

    public static Result addUserToSession(Long sessionId, Long userId) {
        return ok();
    }
}
