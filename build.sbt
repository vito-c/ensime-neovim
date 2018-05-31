fork in run := true
cancelable in Global := true
name := "ensime-neovim"
version := "0.1"
scalaVersion := "2.12.4"
val akkaV       = "2.4.20" //"2.5.3"
val akkaHttpV   = "10.1.1"
val scalaTestV  = "3.0.1"
val ensimeV  = "3.0.0-SNAPSHOT"
import org.ensime.EnsimeCoursierKeys._
import org.ensime.EnsimeKeys._

ensimeRepositoryUrls in ThisBuild += "https://oss.sonatype.org/content/repositories/snapshots/"
ensimeServerVersion in ThisBuild := "3.0.0-SNAPSHOT"
ensimeProjectServerVersion in ThisBuild := "3.0.0-SNAPSHOT"

// val akkaVersion = "2.4.2-RC2"
libraryDependencies ++= Seq(
  //  "org.specs2" %% "specs2-matcher-extra" % "3.6" % Test,
  //  "org.easytesting" % "fest-assert" % "1.4" % Test,
  /* "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test, */
  // "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "3.1.0",
  /* "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0", */
  /* "com.lihaoyi" %% "fastparse" % "0.3.4", */
  /* "com.github.xuwei-k" %% "msgpack4z-core" % "0.2.0", */
  /* "com.github.xuwei-k" % "msgpack4z-java07" % "0.2.0", */
  /* "org.slf4j" % "slf4j-log4j12" % "1.7.12", */
  /* "com.chrisneveu" %% "macrame" % "1.0.1" */
 "com.typesafe.akka" %% "akka-actor" % akkaV,
 "com.typesafe.akka" %% "akka-contrib" % akkaV,
 "com.typesafe.akka" %% "akka-stream" % akkaV,
 "com.typesafe.akka" %% "akka-testkit" % akkaV,
 "com.typesafe.akka" %% "akka-http" % akkaHttpV,
 "com.typesafe.akka" %% "akka-http-core" % akkaHttpV,
//  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
 "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV,
 "com.lihaoyi" %% "pprint" % "0.5.3",
 "org.ensime" %% "api" % ensimeV,
 "org.ensime" %% "json" % ensimeV,
 "vito-c.github.com" %% "scala-neovim" % "0.1"
)

// resolvers += "Glassfish" at "http://maven.glassfish.org/content/repositories/maven.hudson-labs.org"
resolvers += "SonaSnapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += Resolver.mavenLocal
