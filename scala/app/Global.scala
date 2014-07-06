import play.api.mvc.{Results, RequestHeader}
import play.api.GlobalSettings
import scala.concurrent.Future

object Global extends GlobalSettings {
  override def onHandlerNotFound(request: RequestHeader) = {
    import scala.concurrent.ExecutionContext.Implicits.global
    Future(Results.NotFound("Not found").as("text/html"))
  }

  override def getControllerInstance[A](controllerClass: Class[A]) = {
    // typically you'd ask the container to instantiate
    // this and inject necessary dependencies but as our
    // one non-static controller has none we can do this
    controllerClass.newInstance()
  }
}
