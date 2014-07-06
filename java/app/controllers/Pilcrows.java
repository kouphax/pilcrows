package controllers;

import models.Quote;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class Pilcrows extends Controller {

    public static Result index() {
        List<Quote> quotes = new ArrayList<Quote>(){{
            add(new Quote(1, "Dude, sukin’ at something is the first step to being sorta good at something", "Jake the Dog", 2010));
            add(new Quote(2, "Yo", "Or Arbel", 2014));
        }};

        return ok(views.html.index.render(quotes));
    }

    public static Result create() {
        return ok(views.html.create.render());
    }

    public static Result search() {
        return ok(views.html.index.render(new ArrayList<Quote>()));
    }
    
    public static Result edit() {
        return ok(views.html.edit.render());
    }
}
