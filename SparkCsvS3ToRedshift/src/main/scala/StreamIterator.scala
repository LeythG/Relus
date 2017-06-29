package com.hireMe

import org.apache.spark.sql.{ForeachWriter, Row}

/**
  * Created by Leyth Gorgeis on 6/28/2017.
  */
class StreamIterator extends ForeachWriter[Row]{
  var redshiftWriter = new RedshiftWriter
  override def open(partitionId: Long, version: Long): Boolean = {
    true
  }

  override def process(value: Row): Unit = {
    redshiftWriter.writeToNameDateStore(value.get(0).toString.toInt, value.get(1).toString, value.get(2).toString)
  }

  override def close(errorOrNull: Throwable): Unit = {

  }
}
