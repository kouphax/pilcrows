package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import akka.actor.*;
import play.libs.F.*;
import play.mvc.WebSocket;

public class Bonus extends Controller {

    public static Result index() {
        return ok(client.render());
    }

    public static Result sesh() {
        String current = session("counter");
        int next = current == null ? 0 : Integer.parseInt(current) + 1;
        session("counter", "" + next);
        return ok(sesh.render());
    }

//    public static WebSocket<String> socket() {
//        return WebSocket.withActor(new Function<ActorRef, Props>() {
//            public Props apply(ActorRef out) throws Throwable {
//                return EchoActor.props(out);
//            }
//        });
//    }

    public static WebSocket<String> socket() {
        return new WebSocket<String>() {
            public void onReady(WebSocket.In<String> in, final WebSocket.Out<String> out) {
                in.onMessage(new Callback<String>() {
                    public void invoke(String event) {
                        out.write("I received your message: " + event);
                    }
                });
            }
        };
    }

}
