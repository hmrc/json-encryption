import sbt.Keys._
import sbt._

val compileDependencies = PlayCrossCompilation.dependencies(
  shared = Seq(
    "uk.gov.hmrc"       %% "crypto"             % "6.1.0"
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

val scala2_12 = "2.12.16"
val scala2_13 = "2.13.8"

lazy val library = Project("json-encryption", file("."))
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)
  .settings(
    scalaVersion := scala2_12,
    crossScalaVersions := Seq(scala2_12, scala2_13),
    majorVersion := 5,
    isPublicArtefact := true,
    libraryDependencies ++= compileDependencies ++ testDependencies
  )
  .settings(PlayCrossCompilation.playCrossCompilationSettings)
