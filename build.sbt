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

lazy val framework = project
.settings(
moduleName := "scala_fp",
libraryDependencies ++= List(
    "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    "com.eed3si9n" %% "gigahorse-okhttp" % "0.3.1",
    "com.chuusai" %% "shapeless" % "2.3.3"
    )
)