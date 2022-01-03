package dev.leonardo.rifeli.processor

import org.apache.spark.sql.functions.{count, lit, sum}
import org.apache.spark.sql.{DataFrame, Dataset, Encoders, Row, SparkSession}

object BigDataProcessor {

	private val sparkSession: SparkSession = SparkSession.builder()
		.appName("BigDataProcessor")
		.config("spark.driver.allowMultipleContexts", "true")
		.config("spark.dynamicAllocation.enabled", "false")
		.config("spark.sql.caseSensitive", "true")
		.getOrCreate()

	def main(args: Array[String]) {
		val processor = sparkSession.read.format("com.databricks.spark.csv")
				.option("header", true)
				.option("inferSchema", true)
				.option("delimiter", ",")
			  .option("mode", "DROPMALFORMED")
				.csv("hdfs://hadoop.rifeli:9000/bigdata/imdb.csv")

		wordCount(processor)

		sparkSession.close
	}

	private def wordCount(data: DataFrame) = {
		import sparkSession.implicits._
		val processor2136681 = data.filter(a => a.getAs[String]("sentiment") == "neg")
			.map(a => {
				val splitted = a.getAs[String]("text_pt").toLowerCase.split("\\s+")
				(a.getAs[String]("text_pt").toLowerCase.split("\\s+"), splitted.length)
			})
			.toDF("words", "total")
			.agg(count(lit(1)).alias("total"), sum("total").alias("sumTotals"))

		processor2136681.foreach(data => {
			println("RU 2136681")
			println("Count of all negative reviews words is: " + data.getAs[Int]("sumTotals"))
		})

		processor2136681.show()
	}

	private def sumIds(data: DataFrame) = {
		val processor2136681 = data.filter(a => a.getAs[String]("sentiment") == "pos")
			.agg(count(lit(1)).alias("total"), sum("id").alias("sumIds"))

		processor2136681.foreach(data => {
			println("RU 2136681")
			println("Sum of all positive ids: " + data.getAs[Int]("sumIds"))
		})

		processor2136681.show()
	}

}