name := "TypeClassAdhocPolymorphism"

organization := "org.gwgs"

version := "1.0"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.10" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

resolvers += Classpaths.sbtPluginReleases

publishArtifact in Test := false

parallelExecution in Test := false
