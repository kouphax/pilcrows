package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import models.Quote

object Pilcrows extends Controller {

  def index = Action {
    Ok(views.html.index(Quote.selectAll))
  }
  
  def create = Action {
	  Ok(views.html.create())
  }

  def view(id:Long) = Action {
    Quote.selectSingle(id)
      .map(quote => Ok(views.html.view(quote)))
      .getOrElse(Redirect(routes.Pilcrows.index()))
  }
  
  def search = Action {
	  Ok(views.html.index(List.empty))
  }
  
  def edit = Action {
	  Ok(views.html.edit())
  }  
}
