package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import models.Quote

object Pilcrows extends Controller {

  def index = Action {

    val quotes = List(
      Quote(1, "Dude, sukin’ at something is the first step to being sorta good at something", "Jake the Dog", 2010),
      Quote(2, "Yo", "Or Arbel", 2014))

    Ok(views.html.index(quotes))
  }
  
  def create = Action {
	  Ok(views.html.create())
  }
  
  def search = Action {
	  Ok(views.html.index(List.empty))
  }
  
  def edit = Action {
	  Ok(views.html.edit())
  }  
}
