lazy val shinji = (project in file("core")).settings(
  organization := "tf.bug",
  name := "shinji",
  version := "0.1.3",
  scalaVersion := "2.13.4",
  idePackagePrefix := Some("tf.bug.shinji"),
)
