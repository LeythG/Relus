package com.hireMe

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.streaming.{OutputMode, ProcessingTime}
import org.apache.spark.sql.types.StructType

/**
  * Created by Leyth Gorgeis on 6/28/2017.
  */
object StreamFromS3 {
  def start(): Unit ={

    val streamIterator = new StreamIterator

    val spark = SparkSession
      .builder
      .config("spark.dynamicAllocation.enabled", "true")
      .appName("StreamFromS3ToRedshift")
      .master("yarn")
      .getOrCreate()

    spark.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", "")
    spark.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", "")
    spark.sparkContext.hadoopConfiguration.set("mapreduce.input.fileinputformat.input.dir.recursive","true")

    System.setProperty("hadoop.home.dir", "C:\\HadoopUtils\\")

    val csvSchema = ScalaReflection.schemaFor[record].dataType.asInstanceOf[StructType]

    val s3Stream = spark
      .readStream
      .schema(csvSchema)
      .option("maxFilesPerTrigger", "1")
      .option("mode", "DROPMALFORMED")
      .csv("s3n://relushireme/*/*/*/*")

    val s3StreamQuery = s3Stream.select("*")

    val query = s3StreamQuery
      .writeStream
      .format("console")
      .foreach(streamIterator)
      .queryName("S3StreamToRedshift")
      .trigger(ProcessingTime("1 minutes"))
      .outputMode(OutputMode.Append())

    query.start().awaitTermination()
  }
}
