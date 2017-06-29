package com.hireMe

import java.sql.DriverManager
import java.util.Properties

/**
  * Created by Leyth Gorgeis on 6/28/2017.
  */
class RedshiftWriter extends Serializable{

  def writeToNameDateStore(id: Int, name: String, date: String): Unit ={
    val databaseUrl = ""

    val userName = ""
    val password = ""

    val props = new Properties()
    props.setProperty("user", userName)
    props.setProperty("password", password)

    val conn = DriverManager.getConnection(databaseUrl, props)
    val stmt = conn.createStatement()

    val record = s"\ninsert into nameDateStore values('$id','$name','$date');"
    stmt.execute(record)
  }
}
