package org.test.weatherdata

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.generate.weatherdata.GenerateWeatherDataParams
import java.sql.Timestamp

@RunWith(classOf[JUnitRunner])
class WeatherSimulationTest extends FunSuite {
  val generateweatherdataparams = new GenerateWeatherDataParams

  test("Test whether the location comes from Map") {
    val location_test = generateweatherdataparams.getLocation
    assert(GenerateWeatherDataParams.Locations.contains(location_test))
  }

  test("Test Position between min and max range for latitude,longitude and altitude") {
    val position_test = generateweatherdataparams.getPosition
    val latitude_test = position_test.split(",")(0).toFloat
    val longitude_test = position_test.split(",")(1).toFloat
    val altitude_test = position_test.split(",")(2).toFloat
    assert(latitude_test > GenerateWeatherDataParams.minLatitude && latitude_test < GenerateWeatherDataParams.maxPressure
      && longitude_test > GenerateWeatherDataParams.minLongitude && longitude_test < GenerateWeatherDataParams.maxLongitude
      && altitude_test < GenerateWeatherDataParams.maxAltitude)
  }

  test("Test timestamp between the given min and max range") {
    val pressure_test = generateweatherdataparams.getLocaltime
    assert(pressure_test.after(new Timestamp(GenerateWeatherDataParams.minDateTime)) && pressure_test.before(new Timestamp(GenerateWeatherDataParams.maxDateTime)))
  }

  test("Test Conditions occur based on the specified temperature range") {
    val conditionrain_temp_test = GenerateWeatherDataParams.MinMaxRange(10, 27)
    val conditionrain_test = generateweatherdataparams.getConditions(conditionrain_temp_test)
    assert(conditionrain_test.equals("Rain"))
    val conditionsnowy_temp_test = GenerateWeatherDataParams.MinMaxRange(-10, -40)
    val conditionsnowy_test = generateweatherdataparams.getConditions(conditionsnowy_temp_test)
    assert(conditionsnowy_test.equals("Snow"))
    val conditionsunny_temp_test = GenerateWeatherDataParams.MinMaxRange(28, 45)
    val conditionsunny_test = generateweatherdataparams.getConditions(conditionsunny_temp_test)
    assert(conditionsunny_test.equals("Sunny"))
  }

  test("Test Temperature between min and max range") {
    val temperature_test = generateweatherdataparams.getTemperature
    assert(temperature_test > GenerateWeatherDataParams.minTemparature && temperature_test < GenerateWeatherDataParams.maxTemparature)
  }

  test("Test Pressure between min and max range") {
    val pressure_test = generateweatherdataparams.getPressure
    assert(pressure_test > GenerateWeatherDataParams.minPressure && pressure_test < GenerateWeatherDataParams.maxPressure)
  }

  test("Test Humidity between min and max range") {
    val humidity_test = generateweatherdataparams.getHumidity
    assert(humidity_test < GenerateWeatherDataParams.maxHumidity)
  }

}
