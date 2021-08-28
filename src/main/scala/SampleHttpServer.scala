import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import kamon.Kamon

object SampleHttpServer extends SampleRoutes {

  implicit val system = ActorSystem("sample-hello-world")
  implicit val materializer = ActorMaterializer()
  implicit val context = system.dispatcher

  def main(args: Array[String]): Unit = {
    val host  = Option(System.getenv("VCAP_APP_HOST")).getOrElse("localhost")
    val port  = Option(System.getenv("VCAP_APP_PORT")).getOrElse("8080").toInt



    Kamon.init

    val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
    for (i <- 1 to 10) {
      val randomString = new scala.util.Random(31).nextString(10)
      println(s"Random string generated $randomString")
      helloActor ! randomString
    }
    Http().bindAndHandle(sampleRoute, host, port)
  }
}
