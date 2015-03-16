import play.PlayScala

organization := "com.fijimf"

name := """msfcf-server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.5"

resolvers += Resolver.defaultLocal

libraryDependencies ++= Seq(
  "com.fijimf" %% "msfcf-core" % "1.0-SNAPSHOT" ,
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "jquery" % "2.1.3",
  "org.webjars" % "bootstrap" % "3.3.2-2" exclude("org.webjars", "jquery"),
  "org.webjars" % "angularjs" % "1.3.14" exclude("org.webjars", "jquery"),
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.webjars" % "d3js" % "3.5.3"
//  jdbc,
//
//  anorm,
//  cache,
//  ws
)



