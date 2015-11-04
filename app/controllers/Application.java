package controllers;

import com.avaje.ebean.ExpressionList;
import models.Session;
import models.User;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import java.util.*;

public class Application extends Controller {

    public static Result login() {
        return ok(login.render());
    }

    public static Result index(String facebookId) {
        List<User> users = User.find.where().eq("facebookId", facebookId).findList();
        if(users.isEmpty()) {
            return UserController.createUser(facebookId);
        } else {
            return redirect("/user/" + users.get(0).id);
        }
    }

}
