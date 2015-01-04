
name := "se"

version := "1.0"

scalaVersion := "2.11.4"

resolvers += "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.7" % "test"

libraryDependencies += "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.2"

libraryDependencies += "org.antlr" % "antlr" % "3.5" 

libraryDependencies += "commons-io" % "commons-io" % "2.4" 

resolvers += Classpaths.sbtPluginReleases

// addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "0.98.2")

seq(sbtantlr.SbtAntlrPlugin.antlrSettings: _*) 

// ScoverageSbtPlugin.instrumentSettings



