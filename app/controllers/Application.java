package controllers;

import models.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;
import java.util.*;

public class Application extends Controller {

    public static Result login() {
        return ok(login.render());
    }

    public static Result index(String facebookId, String accessToken) {
        List<User> users = User.find.where().eq("facebookId", facebookId).findList();
        if(users.isEmpty()) {
            return UserController.createUser(facebookId, accessToken);
        } else {
            return redirect("/user/" + users.get(0).id);
        }
    }

}
