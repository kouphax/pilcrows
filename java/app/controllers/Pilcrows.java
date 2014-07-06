package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Pilcrows extends Controller {

    public static Result index() {
        return ok(views.html.index.render());
    }

    public static Result create() {
        return ok(views.html.create.render());
    }

    public static Result search() {
        return ok(views.html.search.render());
    }
    
    public static Result edit() {
        return ok(views.html.edit.render());
    }
}
