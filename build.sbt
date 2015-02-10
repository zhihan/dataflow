lazy val commonSettings = Seq(
  organization := "me.zhihan",
  version := "0.1.0",
  scalaVersion := "2.11.5",
  resolvers += "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases",
  libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.7" % "test",
  libraryDependencies += "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.2",
  libraryDependencies += "commons-io" % "commons-io" % "2.4",
  libraryDependencies += "net.liftweb" % "lift-json_2.11" % "2.6"
)



lazy val graph = (project in file("graph")).
  settings(commonSettings: _*).
  settings(
    name := "graph"
  )

lazy val dataflow = (project in file("dataflow")).
  dependsOn(graph).
  settings(commonSettings: _*).
  settings(
    name := "dataflow"
  )

lazy val cg = (project in file("cg")).
  dependsOn(graph).
  settings(commonSettings: _*).
  settings(
    name := "cg",
    libraryDependencies += "org.antlr" % "antlr" % "3.5.2"
  ).
  settings(sbtantlr.SbtAntlrPlugin.antlrSettings: _*)



