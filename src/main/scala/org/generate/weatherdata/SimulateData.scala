package org.generate.weatherdata

import org.apache.spark.sql.SparkSession
import java.sql.Timestamp
import com.typesafe.config.ConfigFactory

/**
 *  Main class which simulates the weather data from the defined methods and writes the simulated
 *  data to a file using Apache Spark
 *  @author vignesh.i
 *  @version 1.0
 *
 *  Format:
 *  bin/spark-submit --class SimulateData --master yarn --deploy-mode cluster JARfilepathwithname
 *
 *  Arguments:
 *  Default arguments are specified in application.conf file, override the conf if required.
 */

/**
 * Schema for the simulated weather dataset.
 *  @param location City to which the weather data belongs to
 *  @param position Position(latitude,longitude,altitude) of the city
 *  @param localtime Localtime of the city
 *  @param Conditions Climatic condition of the city could be any of Rainy,Snow,Sunny
 *  @param temperature Temperature of the city for the specified localtime
 *  @param pressure Pressure of the city
 *  @param humidity Humidity of the city for the specified localtime
 */

case class WeatherData(location: String, position: String, localtime: Timestamp, conditions: String, temperature: Float, pressure: Float, humidity: Int)
object SimulateData {
  val generateweatherdataparams = new GenerateWeatherDataParams
  val propertyconf = ConfigFactory.load() //Loads the property config from application.conf file
  def main(args: Array[String]) {

    val spark = SparkSession.builder.appName("Spark Exercise").master("local[*]").getOrCreate()
    import spark.implicits._
    val input_sequence_number = spark.sqlContext.range(propertyconf.getInt("number_of_rows")).toDF() //Get the number of rows from the config
    //and create a sequence number
    val output_data = input_sequence_number.map { x =>
      val temperature = generateweatherdataparams.getTemperature
      val city = generateweatherdataparams.getLocation
      //Simulate the weatherdata for all the fields
      WeatherData(city, generateweatherdataparams.getCityPosition(city), generateweatherdataparams.getLocaltime, generateweatherdataparams.getConditions(temperature), temperature, generateweatherdataparams.getPressure, generateweatherdataparams.getHumidity)
    }.map { x => x.productIterator.mkString("|") } //Add a demiliter "|" as a product for the case class data generated

    output_data.write.text(propertyconf.getString("outputfilename_withdir")) //write the output data which is a dataset to a file
  }
}