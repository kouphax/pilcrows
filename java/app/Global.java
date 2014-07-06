import play.GlobalSettings;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;


public class Global extends GlobalSettings {
    @Override
    public F.Promise<Result> onHandlerNotFound(Http.RequestHeader requestHeader) {
        return F.Promise.promise(new F.Function0<Result>() {
            @Override
            public Result apply() throws Throwable {
                return Results.notFound("Not found")
                        .as("text/html");
            }
        });
    }
}
