package controllers

import play.api.mvc.{BodyParsers, Action, Controller}
import play.api.libs.json._
import models.Quote

class Api extends Controller {

  implicit val quoteWrites = new Writes[Quote] {
    def writes(quote: Quote) = Json.obj(
      "id"           -> quote.id,
      "quotes"       -> quote.quote,
      "attributedTo" -> quote.attributedTo,
      "year"         -> quote.year
    )
  }

  def index = Action { implicit request =>
    val quotes = Quote.selectAll
    render {
      case Accepts.Json() => Ok(Json.toJson(quotes))
      case Accepts.Xml() => {
        val asXml = quotes.map { quote =>
          <quote>
            <id>{quote.id}</id>
            <quote>{quote.quote}</quote>
            <attributedTo>{quote.attributedTo}</attributedTo>
            <year>{quote.year}</year>
          </quote>
        }

        Ok(<quotes>{asXml}</quotes>)
      }
    }

  }

  def search = Action { implicit request =>
    request.getQueryString("query").map { query =>
      val quotes = Quote.search(query)
      Ok(Json.toJson(quotes))
    } getOrElse {
      Ok(Json.obj())
    }
  }

  def create = Action(parse.json) { implicit request =>
    Quote.form.bind(request.body).fold(
      errors => BadRequest(errors.errorsAsJson),
      quote => {
        Quote.save(quote)
        Created(Json.toJson(quote))
      }
    )
  }
}
