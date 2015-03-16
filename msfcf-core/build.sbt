organization := """com.fijimf"""

//publishTo := Some(Resolver.defaultLocal)

name := """msfcf-core"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.5"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
libraryDependencies += "joda-time" % "joda-time" % "2.7"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9"

