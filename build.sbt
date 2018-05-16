fork in run := true
cancelable in Global := true
name := "ensime-neovim"
version := "0.1"
scalaVersion := "2.11.7"


val akkaVersion = "2.4.2-RC2"
libraryDependencies ++= Seq(
  //  "org.specs2" %% "specs2-matcher-extra" % "3.6" % Test,
  //  "org.easytesting" % "fest-assert" % "1.4" % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  // "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "3.1.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "com.lihaoyi" %% "fastparse" % "0.3.4",
  "com.github.xuwei-k" %% "msgpack4z-core" % "0.2.0",
  "com.github.xuwei-k" % "msgpack4z-java07" % "0.2.0",
  "org.slf4j" % "slf4j-log4j12" % "1.7.12",
  "com.chrisneveu" %% "macrame" % "1.0.1"
)

resolvers += "Glassfish" at "http://maven.glassfish.org/content/repositories/maven.hudson-labs.org"
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
