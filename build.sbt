
name := "se"

version := "1.0"

resolvers += "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test"

libraryDependencies += "org.antlr" % "antlr" % "3.4" // java

libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test" // scala

TaskKey[Unit]("antlr") := { }