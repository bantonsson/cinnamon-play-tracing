lazy val cinnamonPlayTracing = project
  .in(file("."))
  .aggregate(frontend, service, backend)

lazy val frontend = project
  .in(file("frontend"))
  .enablePlugins(PlayScala, Cinnamon)
  .settings(
    scalaVersion := "2.12.6",
    libraryDependencies += guice,
    libraryDependencies += ws,
    libraryDependencies += Cinnamon.library.cinnamonOpenTracingZipkin,
    libraryDependencies += Cinnamon.library.cinnamonPlay,
    libraryDependencies += Cinnamon.library.cinnamonJvmMetricsProducer,
    libraryDependencies += Cinnamon.library.cinnamonCHMetrics
  )

lazy val service = project
  .in(file("service"))
  .enablePlugins(PlayScala, Cinnamon)
  .settings(
    scalaVersion := "2.12.6",
    libraryDependencies += guice,
    libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.5.14",
    libraryDependencies += Cinnamon.library.cinnamonOpenTracingZipkin,
    libraryDependencies += Cinnamon.library.cinnamonPlay,
    libraryDependencies += Cinnamon.library.cinnamonJvmMetricsProducer,
    libraryDependencies += Cinnamon.library.cinnamonCHMetrics
  )

lazy val backend = project
  .in(file("backend"))
  .enablePlugins(Cinnamon)
  .settings(
    scalaVersion := "2.12.6",
    libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.5.14",
    libraryDependencies += Cinnamon.library.cinnamonOpenTracingZipkin,
    libraryDependencies += Cinnamon.library.cinnamonAkka,
    libraryDependencies += Cinnamon.library.cinnamonJvmMetricsProducer,
    libraryDependencies += Cinnamon.library.cinnamonCHMetrics,
    cinnamon in run := true,
    connectInput in run := true // we wait on stdin
  )

