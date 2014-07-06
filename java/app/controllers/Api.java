package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Quote;
import play.data.Form;
import play.libs.Json;
import play.libs.XML;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

public class Api extends Controller {

    static Form<Quote> form = Form.form(Quote.class);

    public static Result index() {
        List<Quote> quotes = Quote.find.all();

        if(request().accepts("application/xml")) {
            return ok("<quotes>I'm to lazy to do this.</quotes>");
        } else {
            return ok(Json.toJson(quotes));
        }
    }

    public static Result search() {
        String query = request().getQueryString("query");
        if(query == null) {
            return ok(Json.newObject());
        } else {
            List<Quote> matches = Quote.find
                    .where()
                    .ilike("quote", "%" + query + "%")
                    .findList();
            return ok(Json.toJson(matches));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result create() {
        JsonNode json = request().body().asJson();
        Form<Quote> quoteForm = form.bind(json);

        if(quoteForm.hasErrors()) {
            return badRequest(quoteForm.errorsAsJson());
        } else {
            Quote quote = quoteForm.get();
            quote.save();
            return status(Http.Status.CREATED, Json.toJson(quote));
        }
    }
}
