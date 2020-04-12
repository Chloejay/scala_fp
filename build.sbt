val Scala_212 = "2.12.11"

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


lazy val framework = project in file(".").
    settings(
    moduleName := "scala_fp",
    libraryDependencies ++= List(
        "org.scalatest" %% "scalatest" % "3.0.5" % Test,
        "org.typelevel" %% "cats-effect" % "2.0.0",
        "org.typelevel" %% "cats-core" % "2.0.0",
        "org.typelevel" %% "cats" % "0.9.0"
        )
    )
scalacOptions += "-Ypartial-unification"