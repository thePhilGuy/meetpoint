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
        System.out.println("fb Id" + facebookId);
        System.out.println("token" + accessToken);
        List<User> users = User.find.where().eq("facebookId", facebookId).findList();
        if(users.isEmpty()) {
            return UserController.createUser(facebookId, accessToken);
        } else {
            users.get(0).updateFacebookToken(accessToken);
            return redirect("/user/" + users.get(0).id);
        }
    }

}
