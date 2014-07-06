import play.api.mvc.{Results, RequestHeader}
import play.api.GlobalSettings
import scala.concurrent.Future

object Global extends GlobalSettings {
  override def onHandlerNotFound(request: RequestHeader) = {
    import scala.concurrent.ExecutionContext.Implicits.global
    Future(Results.NotFound("Not found").as("text/html"))
  }
}
