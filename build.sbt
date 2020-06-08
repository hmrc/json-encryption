import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning

val appName = "json-encryption"


  val compileDependencies = PlayCrossCompilation.dependencies(
    shared = Seq(
      "uk.gov.hmrc"       %% "crypto"    % "5.6.0"
    ),
    play25 = Seq(
      "com.typesafe.play" %% "play-json"        % "2.5.19",
      "uk.gov.hmrc"       %% "http-verbs"       % "10.7.0-play-25"
    ),
    play26 = Seq(
      "com.typesafe.play" %% "play-json"        % "2.6.14",
      "uk.gov.hmrc"       %% "http-verbs"       % "10.7.0-play-26"
    ),
    play27 = Seq(
      "com.typesafe.play" %% "play-json"        % "2.7.4",
      "uk.gov.hmrc"       %% "http-verbs"       % "10.7.0-play-27"
    )
  )

  val testDependencies = PlayCrossCompilation.dependencies(
    shared = Seq(
      "org.scalatest"          %% "scalatest"    % "3.1.2"   % "test",
      "com.vladsch.flexmark"   %  "flexmark-all" % "0.35.10" % "test"
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
