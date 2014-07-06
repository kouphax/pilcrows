package controllers

import play.api.mvc.Controller
import play.api.mvc.Action

object Pilcrows extends Controller {
  
  def index = Action {
    Ok(views.html.index())
  }
  
  def create = Action {
	Ok(views.html.create())
  }
  
  def search = Action {
	Ok(views.html.index())
  }
  
  def edit = Action {
	Ok(views.html.edit())
  }  
}
