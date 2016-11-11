/*
 * Copyright 2015 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import sbt.Keys._
import sbt._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {
  import Dependencies._
  import uk.gov.hmrc.DefaultBuildSettings
  import uk.gov.hmrc.DefaultBuildSettings._

  val nameApp = "json-encryption"

  val appDependencies = Seq(
    Compile.playJson,
    Compile.crypto,
    Compile.secure,

    Test.scalaTest,
    Test.pegdown
  )

  lazy val jsonEncryption = Project(nameApp, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      scalaVersion := "2.11.7",
      libraryDependencies ++= appDependencies,
      crossScalaVersions := Seq("2.11.7"),
      organization := "uk.gov.hmrc",
      resolvers := Seq(
        Resolver.bintrayRepo("hmrc", "releases"),
        "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/"
      )
    )
}

object Dependencies {

  object Compile {
    val playJson = "com.typesafe.play" %% "play-json" % "2.5.8" % "provided"
    val secure = "uk.gov.hmrc" %% "secure" % "7.1.0"
    val crypto = "uk.gov.hmrc" %% "crypto" % "4.1.0"
  }

  sealed abstract class Test(scope: String) {
    val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % scope
    val pegdown = "org.pegdown" % "pegdown" % "1.5.0" % scope
  }

  object Test extends Test("test")

}


