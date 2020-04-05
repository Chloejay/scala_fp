ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "com.chloeji"
ThisBuild / homepage := Some(url("https://github.com/Chloejay/scala_fp"))

lazy val f_p = (project in file("."))
  .settings(
    name := "f_p",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.eed3si9n" %% "gigahorse-okhttp" % "0.3.1",
  )