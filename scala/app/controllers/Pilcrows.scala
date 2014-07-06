package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import models.Quote

object Pilcrows extends Controller {

  def index = Action {
    Ok(views.html.index(Quote.selectAll))
  }
  
  def create = Action {
	  Ok(views.html.create(Quote.form))
  }

  def save = Action { implicit request =>
    Quote.form.bindFromRequest.fold(
      errors => BadRequest(views.html.create(errors)),
      quote => {
        Quote.save(quote)
        Redirect(routes.Pilcrows.index())
      }
    )
  }

  def view(id:Long) = Action {
    Quote.selectSingle(id)
      .map(quote => Ok(views.html.view(quote)))
      .getOrElse(Redirect(routes.Pilcrows.index()))
  }
  
  def search = Action { implicit request =>
    request.getQueryString("query")
      .map(query => Ok(views.html.index(Quote.search(query))))
      .getOrElse(Redirect(routes.Pilcrows.index()))
  }
  
  def edit(id: Long) = Action {
    Quote.selectSingle(id)
      .map(quote => Ok(views.html.edit(id, Quote.form.fill(quote))))
      .getOrElse(Redirect(routes.Pilcrows.index()))
  }

  def update(id: Long) = Action { implicit request =>
    Quote.form.bindFromRequest.fold(
      errors => BadRequest(views.html.edit(id, errors)),
      quote => {
        Quote.update(id, quote)
        Redirect(routes.Pilcrows.index())
      }
    )
  }

  def delete(id: Long) = Action { implicit request =>
    Quote.delete(id)
    Redirect(routes.Pilcrows.index())
  }
}
