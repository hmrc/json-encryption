import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning

val appName = "json-encryption"

val dependencies = Seq(
  "com.typesafe.play" %% "play-json" % "2.5.12" % "provided",
  "uk.gov.hmrc"       %% "crypto"    % "5.1.0",
  "org.scalatest"     %% "scalatest" % "3.0.1" % "test",
  "org.pegdown"       % "pegdown"    % "1.6.0" % "test"
)

lazy val library = Project(appName, file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    libraryDependencies ++= dependencies,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    ),
    majorVersion := 4,
    makePublicallyAvailableOnBintray := true
  )
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)
