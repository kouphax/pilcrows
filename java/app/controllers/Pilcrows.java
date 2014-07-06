package controllers;

import models.Quote;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class Pilcrows extends Controller {

    static Form<Quote> form = Form.form(Quote.class);

    public static Result index() {
        return ok(views.html.index.render(Quote.find.all()));
    }

    public static Result create() {
        return ok(views.html.create.render(form));
    }

    public static Result save() {
        Form<Quote> quoteForm = form.bindFromRequest();

        if(quoteForm.hasErrors()) {
            return badRequest(views.html.create.render(quoteForm));
        } else {
            quoteForm.get().save();
            return redirect(routes.Pilcrows.index());
        }
    }

    public static Result search() {
        return ok(views.html.index.render(new ArrayList<Quote>()));
    }
    
    public static Result edit() {
        return ok(views.html.edit.render());
    }

    public static Result view(Long id) {
        Quote quote = Quote.find.byId(id);
        if(quote == null) {
            return redirect(routes.Pilcrows.index());
        } else {
            return ok(views.html.view.render(Quote.find.byId(id)));
        }
    }
}
