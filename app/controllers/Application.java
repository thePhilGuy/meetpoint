package controllers;

import models.Session;
import models.User;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result login() {
        return ok(login.render());
    }

    public static Result index() {
        return ok(index.render("Hello world"));
    }

}
