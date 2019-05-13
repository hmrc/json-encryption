import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning

val appName = "json-encryption"

val play25 = "2.5.19"
val play26 = "2.6.20"

  val compileDependencies = PlayCrossCompilation.dependencies(
    shared = Seq(
      "uk.gov.hmrc"       %% "crypto"    % "5.4.0"
    ),
    play25 = Seq(
      "com.typesafe.play" %% "play-json"        % "2.5.19",
      "uk.gov.hmrc"       %% "http-verbs"       % "9.7.0-play-25"
    ),
    play26 = Seq(
      "com.typesafe.play" %% "play-json"        % "2.6.13",
      "uk.gov.hmrc"       %% "http-verbs"       % "9.7.0-play-26"
    )
  )

  val testDependencies = PlayCrossCompilation.dependencies(
    shared = Seq(
      "org.scalatest" %% "scalatest"  % "3.0.5" % "test",
      "org.pegdown"   % "pegdown"     % "1.6.0" % "test"
    )
  )

lazy val library = Project(appName, file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    libraryDependencies ++= compileDependencies ++ testDependencies,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    ),
    majorVersion := 4,
    scalaVersion := "2.11.12",
    crossScalaVersions := Seq("2.11.12", "2.12.8"),
    makePublicallyAvailableOnBintray := true
  )
  .settings(PlayCrossCompilation.playCrossCompilationSettings)
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)
