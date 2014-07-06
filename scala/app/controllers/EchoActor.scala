package controllers

import akka.actor._

object EchoActor {
  def props(out: ActorRef) = Props(new EchoActor(out))
}

class EchoActor(out: ActorRef) extends Actor {
  def receive = {
    case msg: String =>
      out ! ("I received your message: " + msg)
  }
}