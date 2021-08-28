import com.typesafe.sbt.packager.docker._

name := "kamon-actor-metrics-collector"

version := "0.1"

scalaVersion := "2.12.12"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.7",
  "com.typesafe.akka" %% "akka-stream" % "2.5.23",
  "com.typesafe.akka" %% "akka-actor" % "2.5.23",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7",
  "io.kamon" %% "kamon-core" % "2.2.2",
  "io.kamon" %% "kamon-akka" % "2.2.2",
  "io.kamon" %% "kamon-prometheus" % "2.2.2"
)


lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging, JavaAgent)

javaAgents += "io.kamon" % "kanela-agent" % "1.0.11"

//enablePlugins(JavaAppPackaging)
//enablePlugins(DockerPlugin)
addCommandAlias("pkgkamon", ";clean;universal:packageBin")