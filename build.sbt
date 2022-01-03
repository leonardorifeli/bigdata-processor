name := "BigDataProcessor"
organization := "dev.leonardo.rifeli"
version := "0.1"
scalaVersion := "2.11.12"

mainClass := Some("dev.leonardo.rifeli.processor.BigDataProcessor")

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0"
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "3.1.1"