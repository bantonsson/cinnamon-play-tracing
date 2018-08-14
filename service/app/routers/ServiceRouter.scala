package routers

import com.lightbend.cinnamon.akka.http.scaladsl.server.Endpoint
import controllers.Service
import javax.inject.Inject
import play.api.mvc._
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class ServiceRouter @Inject() (controller: Service)
  extends SimpleRouter {
  override def routes: Routes = test2Routes

  private val test2Routes: Routes = {
    case GET(p"/request/$message") =>
      Endpoint.withName("/request") {
        controller.request(message)
      }
  }
}
