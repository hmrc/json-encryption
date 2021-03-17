import sbt.Keys._
import sbt._

val compileDependencies = PlayCrossCompilation.dependencies(
  shared = Seq(
    "uk.gov.hmrc"       %% "crypto"             % "6.0.0"
  ),
  play26 = Seq(
    "com.typesafe.play" %% "play-json"          % "2.6.14",
    "uk.gov.hmrc"       %% "http-verbs-play-26" % "13.2.0"
  ),
  play27 = Seq(
    "com.typesafe.play" %% "play-json"          % "2.7.4",
    "uk.gov.hmrc"       %% "http-verbs-play-27" % "13.2.0"
  ),
  play28 = Seq(
    "com.typesafe.play" %% "play-json"          % "2.8.1",
    "uk.gov.hmrc"       %% "http-verbs-play-28" % "13.2.0"
  )
)

val testDependencies = PlayCrossCompilation.dependencies(
  shared = Seq(
    "org.scalatest"          %% "scalatest"    % "3.1.2"   % Test,
    "com.vladsch.flexmark"   %  "flexmark-all" % "0.35.10" % Test
  )
)

lazy val library = Project("json-encryption", file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    libraryDependencies ++= compileDependencies ++ testDependencies,
    majorVersion := 4,
    scalaVersion := "2.12.13",
    makePublicallyAvailableOnBintray := true
  )
  .settings(PlayCrossCompilation.playCrossCompilationSettings)
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)
