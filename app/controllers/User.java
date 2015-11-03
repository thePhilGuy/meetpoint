package controllers;

import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;
import views.html.session;
import views.html.user;
import java.util.List;

public class User extends Controller {

    public static Result createUser() {
        models.User user = Form.form(models.User.class).bindFromRequest().get();
        user.save();
        return redirect("/user/" + user.id);
    }

    public static Result showUser(Long userId) {
        List<models.Session> list = getSessionsByUser(userId);
        return ok(user.render(""));
    }

    public static List<models.Session> getSessionsByUser(Long userId) {
        List<models.Session> sessions = new Model.Finder(String.class, models.Session.class).all();
        return sessions;
    }

}
