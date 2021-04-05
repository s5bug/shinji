lazy val shinji = (project in file("core")).settings(
  organization := "tf.bug",
  name := "shinji",
  version := "0.1.4",
  scalaVersion := "2.13.5",
  idePackagePrefix := Some("tf.bug.shinji"),
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.3" cross CrossVersion.full),
)
