package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee._
import play.api.mvc._

object Bonus extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.client())
  }

  def sesh = Action { implicit request =>
    val counter = request.session.get("counter").map(_.toInt).getOrElse(0)
    Ok(views.html.sesh()).withSession("counter" -> (counter + 1).toString)
  }

//  def socket = WebSocket.acceptWithActor[String, String] { request => out =>
//    EchoActor.props(out)
//  }

  def socket = WebSocket.using[String] { request =>
    val (out, channel) = Concurrent.broadcast[String]
    val in = Iteratee.foreach[String] { msg =>
      channel.push("I received your message: " + msg)
    }
    (in, out)
  }
}