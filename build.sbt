import sbt.Keys._
import sbt._

val compileDependencies = PlayCrossCompilation.dependencies(
  shared = Seq(
    "uk.gov.hmrc"       %% "crypto"             % "6.1.0"
  ),
  play26 = Seq(
    "com.typesafe.play" %% "play-json"          % "2.6.14"
  ),
  play27 = Seq(
    "com.typesafe.play" %% "play-json"          % "2.7.4"
  ),
  play28 = Seq(
    "com.typesafe.play" %% "play-json"          % "2.8.1"
  )
)

val testDependencies = PlayCrossCompilation.dependencies(
  shared = Seq(
    "org.scalatest"          %% "scalatest"    % "3.1.2"   % Test,
    "com.vladsch.flexmark"   %  "flexmark-all" % "0.35.10" % Test
  )
)

val scala2_12 = "2.12.15"
val scala2_13 = "2.13.7"

lazy val library = Project("json-encryption", file("."))
  .settings(
    scalaVersion := scala2_12,
    crossScalaVersions := Seq(scala2_12, scala2_13),
    majorVersion := 4,
    isPublicArtefact := true,
    libraryDependencies ++= compileDependencies ++ testDependencies
  )
  .settings(PlayCrossCompilation.playCrossCompilationSettings)
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)
