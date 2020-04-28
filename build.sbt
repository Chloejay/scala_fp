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

lazy val fp = (project in file(".")).
    settings(
    moduleName := "scala_fp",
    libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.0.5" % Test,
        "org.typelevel" %% "cats-effect" % "2.0.0",
        "org.typelevel" %% "cats-core" % "2.0.0",
        "org.typelevel" %% "cats" % "0.9.0",
        "org.scalaz" %% "scalaz-core" % "7.2.30",
        "org.scalaz" %% "scalaz-effect" % "7.2.7",
        "org.scalaz" %% "scalaz-typelevel" % "7.1.17",
        "org.scalaz" %% "scalaz-scalacheck-binding" % "7.3.0-M32" % "test",
        "org.scalacheck" %% "scalacheck" % "1.14.1" % "test",
        "org.scalariform" %% "scalariform" % "0.2.10"
        // "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0"
        )
    )
scalacOptions += "-Ypartial-unification"
// initialCommands in console := "import scalaz._, Scalaz._"