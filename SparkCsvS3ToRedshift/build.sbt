name := "SparkCsvS3ToRedshift"

version := "1.0"

scalaVersion := "2.11.11"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core_2.11
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.1.1"

dependencyOverrides ++= Set("com.fasterxml.jackson.core" % "jackson-databind" % "2.6.6")