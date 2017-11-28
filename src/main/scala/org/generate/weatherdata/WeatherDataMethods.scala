package org.generate.weatherdata

import java.sql.Timestamp
/**
 * This is the base trait for WeatherDataSimulation. It provides a method for each parameter
 *  which then combines to generate the weather simulation dataset.
 *  @author vignesh.i
 *  @version 1.0
 */
trait WeatherDataMethods {
  /**
   * Gets the location from the pre-defined map.
   * @return String
   */
  def getLocation: String
  /**
   * Gets the Position which includes longitude,latitude,altitude based on the range provided.
   * @return String
   */
  def getPosition: String
  /**
   * Gets the LocalTime based on the after and before datetime provided.
   * @return Timestamp
   */
  def getLocaltime: Timestamp
  /**
   * Gets the Condition based on the temperature provided.
   * @return String
   */
  def getConditions(temparature: Float): String
  /**
   * Gets the Temperature based on the pre-defined min and max range.
   * @return Float
   */
  def getTemperature: Float
  /**
   * Gets the Pressure based on the pre-defined min and max range.
   * @return Float
   */
  def getPressure: Float
  /**
   * Gets the Humidity based on the pre-defined min and max range.
   * @return Int
   */
  def getHumidity: Int

}