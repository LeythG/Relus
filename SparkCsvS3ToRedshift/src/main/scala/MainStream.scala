package com.hireMe
/**
  * Created by Leyth Gorgeis on 6/29/2017.
  */
object MainStream{
  def main(args: Array[String]): Unit = {
    val streamFromS3 = StreamFromS3
    streamFromS3.start()
  }
}
