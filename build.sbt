val Scala_212 = "2.12.11"
val cats_core = "org.typelevel"       %% "cats-core"         % "2.0.0"
val cats_effect = "org.typelevel"     %% "cats-effect"       % "2.0.0"
val munit = "org.scalameta" %% "munit" % "0.7.4" % Test
val scalazcore = "org.scalaz"          %% "scalaz-core"      % "7.2.30"
val scalazeffect = "org.scalaz"        %% "scalaz-effect"    % "7.2.7"
val scalaztypelevel = "org.scalaz"     %% "scalaz-typelevel" % "7.1.17"
val kindProjector = compilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")

inThisBuild(List(
scalaVersion := Scala_212,
organization := "com.chloejay",
homepage := Some(url("https://github.com/Chloejay/scala_fp")),
developers := List(
Developer(
    "chloe",
    "Chloe Ji",
    "chloejiy@gmail.com",
    url("https://chloejay.github.io/")
)))
)

lazy val fp = (project in file(".")).
settings(moduleName := "scala_fp",
description := "practice functional programming with Scala",
libraryDependencies ++= Seq(
    cats_core,
    cats_effect,
    kindProjector,
    munit
    ),
testFrameworks += new TestFramework("munit.Framework")
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:postfixOps",
  "-language:higherKinds",
  "-Ypartial-unification")