import java.util.concurrent.ConcurrentHashMap.Segment

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

trait SampleRoutes {
  private lazy val getPath = get

  private lazy val basePostEmailPath = pathPrefix("postemail")
  val newSystem = ActorSystem("HelloSystem")

  val helloActor = newSystem.actorOf(Props[HelloActor], name = "helloactor")

  val sampleRoute: Route = pathPrefix("sample") {
    getPath {
      val randomString = new scala.util.Random(31).nextString(10)
      println(s"Random string generated $randomString")
      helloActor ! "randomString"


      println(s"Actror path :: ${helloActor.path.toString}")
      complete(StatusCodes.OK, "Hello world!")
    }
  }
}


class HelloActor extends Actor {
  override def receive: Receive = {
    case "hello" => println("Hello back to you!!")
    case value => {
      println(s"I didn't get you, but this is what I see from you :: $value")

      Thread.sleep(1 * 1000)
    }
  }
}