lazy val shinji = (project in file("core")).settings(
  organization := "tf.bug",
  name := "shinji",
  version := "0.2.0",
  scalaVersion := "2.13.7",
  idePackagePrefix := Some("tf.bug.shinji"),
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full),
)
