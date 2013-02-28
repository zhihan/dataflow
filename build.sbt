
name := "se"

version := "1.0"

resolvers += "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "org.antlr" % "antlr" % "3.5" 

scalaVersion := "2.10.0"

seq(sbtantlr.SbtAntlrPlugin.antlrSettings: _*)



