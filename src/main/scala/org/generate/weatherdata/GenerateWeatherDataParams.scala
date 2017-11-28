package org.generate.weatherdata

import scala.util.Random
import java.sql.Timestamp
import scala.collection.concurrent.TrieMap

/**
 *  A companion object which holds the predefined values for different parameters used in 
 *  weather data simulation 
 *  @author vignesh.i
 *  @version 1.0
 */

object GenerateWeatherDataParams{
  /** This is the minimum latitude in earth*/
    final val minLatitude = -90.0f
  /** This is the maximum latitude in earth*/
    final val maxLatitude = 90.0f
  /** This is the maximum longitude in earth*/
    final val minLongitude = -180.0f
  /** This is the maximum longitude in earth*/
    final val maxLongitude = 180.0f
  /** This is the maximum altitude considered for this exercise based on the location chosen*/
    final val maxAltitude = 3000
  /** This is the minimum temperature considered for this exercise based on the location chosen*/
    final val minTemparature = -3.0f
  /** This is the maximum temperature considered for this exercise based on the location chosen*/
    final val maxTemparature = 45.0f
  /** This is the maximum pressure chosen based on wiki*/
    final val maxPressure = 1100.0f
  /** This is the minimum pressure chosen based on wiki*/
    final val minPressure = 550.0f
  /** This is the maximum humidity chosen based on wiki*/
    final val maxHumidity = 100
  /** This is the starting localtimestamp chosen for this exercise*/
    final val minDateTime = Timestamp.valueOf("1990-01-01 00:00:00").getTime();
  /** This is the ending localtimestamp chosen for this exercise*/
	  final val maxDateTime = Timestamp.valueOf("2017-11-27 00:00:00").getTime();
	/** A Pre-defined vector which contains the location*/
    final val Locations = Vector("Chennai","Ooty","Delhi","Mumbai","Sydney")
  
  /** Generates a random integer number with a max limit of the given range
   *  @param maxrange Maximum value till which the method can generate  
   *  @return Int 
   */
    def MaxRange(maxrange:Int):Int = {
      Random.nextInt(maxrange)
    }
    
   /** Generates a random number between the specified min and max range
   *  @param maxrange Maximum value till which the method can generate
   *  @param minrange Minimum value till which the method can generate    
   *  @return Float 
   */
    
    def MinMaxRange(minrange:Float,maxrange:Float):Float= {
      Random.nextFloat() * (maxrange - minrange) + minrange
    }
    
}

/**
 *  A companion class which implements the methods defined in WeatherDataMethods trait.
 *  Each parameter in the weather data would have a corresponding method here to simulate the dataset
 *  @author vignesh.i
 *  @version 1.0
 */

class GenerateWeatherDataParams extends WeatherDataMethods {
  
  /** Map which maintains the position for a city. When queried successively the position is retrieved 
   *  from the map
   */
  val CityPositionMap=new TrieMap[String,String]()
  
  def getLocation:String= {
    
    GenerateWeatherDataParams.Locations(Random.nextInt(GenerateWeatherDataParams.Locations.size))
  }
  def getPosition:String= {
    val Latitude = GenerateWeatherDataParams.MinMaxRange(GenerateWeatherDataParams.minLatitude,GenerateWeatherDataParams.maxLatitude)
    val Longitude = GenerateWeatherDataParams.MinMaxRange(GenerateWeatherDataParams.minLongitude,GenerateWeatherDataParams.maxLongitude)
    val Altitude = GenerateWeatherDataParams.MaxRange(GenerateWeatherDataParams.maxAltitude)
    Latitude+","+Longitude+","+Altitude
  }
  def getLocaltime:Timestamp = {
    val difftimestamp = GenerateWeatherDataParams.maxDateTime - GenerateWeatherDataParams.minDateTime + 1
		val random_timestamp = new Timestamp(GenerateWeatherDataParams.minDateTime +  (Math.random() * difftimestamp).toLong)
		random_timestamp
  }
  
  def getConditions(temparature:Float):String ={
    
    if(temparature > 10 && temparature < 27) "Rain"
    else if(temparature < 10) "Snow"
    else "Sunny"
  }
  
  def getTemperature:Float= {
    GenerateWeatherDataParams.MinMaxRange(GenerateWeatherDataParams.minTemparature,GenerateWeatherDataParams.maxTemparature)
  }
  
  def getPressure:Float= {
    GenerateWeatherDataParams.MinMaxRange(GenerateWeatherDataParams.minPressure,GenerateWeatherDataParams.maxPressure)
  }
  
  def getHumidity:Int= {
    GenerateWeatherDataParams.MaxRange(GenerateWeatherDataParams.maxHumidity)
  }
  
  /** Generates a random position for the first time for a city. For sucessive hits returns previously generated position.
   *  @param City City for which the position needs to be retrieved or generated newly
   *  @return String 
   */  
  def getCityPosition(City:String):String = {
    if(!CityPositionMap.contains(City))
      CityPositionMap.put(City, getPosition)
    
    CityPositionMap.apply(City)
  }
}