scalaVersion := "2.11.7"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com",
  "ConJars" at "http://conjars.org/repo",
  "CloJars" at "http://clojars.org/repo"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xlog-free-types", "-Xlog-free-terms")

traceLevel := -1

logLevel := Level.Info

// disable printing timing information, but still print [success]
showTiming := false

// disable printing a message indicating the success or failure of running a task
showSuccess := false

offline := true

libraryDependencies ++= List("org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scalatest" %% "scalatest" % "2.2.6",
  "org.apache.spark" %% "spark-core" % "1.6.1",
  "org.apache.spark" %% "spark-sql" % "1.6.1",
  "com.twitter.finatra" %% "finatra-http" % "2.1.5" exclude("org.slf4j","log4j-over-slf4j"),
  "cascading.kryo" % "cascading.kryo" % "0.4.7",
  "com.twitter" %% "scalding-commons" % "0.15.0",
  "com.twitter" %% "scalding-core" % "0.15.0",
  "com.twitter" %% "scalding-args" % "0.15.0"
)

addCommandAlias("part1_1", "~ test-only part1_1")

addCommandAlias("part1_2", "~ test-only part1_2")

addCommandAlias("part2", "~ test-only part2")

addCommandAlias("go", "~ test-only HandsOnScala")

addCommandAlias("server", "test:runMain stock.StockServer -http.port=:8080 -local.doc.root=./src/test/scala/part3")

addCommandAlias("spark", "~ test-only sparkintro")

fork in (Test, run) := true

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    // if scala 2.11+ is used, quasiquotes are merged into scala-reflect
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value
    // in Scala 2.10, quasiquotes are provided by macro paradise
    case Some((2, 10)) =>
      libraryDependencies.value ++ Seq(
        compilerPlugin("org.scalamacros" % "paradise" % "2.0.0" cross CrossVersion.full),
        "org.scalamacros" %% "quasiquotes" % "2.0.0" cross CrossVersion.binary)
  }
}
