//import akka.actor.Actor
//import akka.actor.ActorSystem
//import akka.actor.Props
//
//object Main extends App {
//
//  println("Hello world")
//
//  val system = ActorSystem("HelloSystem")
//
//  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
//
//  helloActor ! "hello"
//
//  helloActor ! "Hey barry"
//}
//
//
//
//class HelloActor extends Actor {
//  override def receive: Receive = {
//    case "hello" => println("Hello back to you!!")
//    case value => println(s"I didn't get you, but this is what I see from you :: $value")
//  }
//}

object Main {

}