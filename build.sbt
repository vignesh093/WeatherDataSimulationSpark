
name := "WeatherDataSimulation"
version := "1.0"
scalaVersion := "2.11.8"


libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.2.0.cloudera1" % "provided"

libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.2.0.cloudera1" % "provided"

libraryDependencies += "com.typesafe" % "config" % "1.3.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

// https://mvnrepository.com/artifact/junit/junit
libraryDependencies += "junit" % "junit" % "4.10" % Test

resolvers ++= Seq(
"Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
"cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos",
"MavenRepository" at "https://mvnrepository.com/"
)


assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", "spark", "unused", "UnusedStubClass.class")         => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
   oldStrategy(x)
}