package com.example.mvvmweather.data.models

import android.annotation.SuppressLint
import android.graphics.Color
import com.example.mvvmweather.util.HLog
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*


/**
 * Developed by hasham on 2/1/18.
 */

data class ApiResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("daily")
    val daily: List<Daily>,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int
)

data class Current(
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double
){
    val dateTime: String
        get(): String {
            return try {
                HLog.e("dateTime",dt.toString())
                val date = Date(dt* 1000)
                val df2 = SimpleDateFormat("E dd - MMMM", Locale.US)
                df2.format(date)
            } catch (e: Exception) {
                return ""
            }
        }

    val todayText: String
        get(): String {
            return try {
               return String.format("Today is %s °C with %s",temp,weather[0].description)
            } catch (e: Exception) {
                return ""
            }
        }
}

data class Daily(
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("moon_phase")
    val moonPhase: Double,
    @SerializedName("moonrise")
    val moonrise: Int,
    @SerializedName("moonset")
    val moonset: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("rain")
    val rain: Double,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
    @SerializedName("temp")
    val temp: Temp,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    var expanded : Boolean = false
){
    val dateTime: String
        get(): String {
            return try {
                val date = Date(dt* 1000)
                val df2 = SimpleDateFormat("E dd - MMMM", Locale.US)
                df2.format(date)
            } catch (e: Exception) {
                return ""
            }
        }

    val sunsetTime: String
        get(): String {
            return try {
                val date = Date(sunset* 1000)
                val df2 = SimpleDateFormat("hh:mm a", Locale.US)
                df2.format(date)
            } catch (e: Exception) {
                return ""
            }
        }

    val sunriseTime: String
        get(): String {
            return try {
                val date = Date(sunrise* 1000)
                val df2 = SimpleDateFormat("hh:mm a", Locale.US)
                df2.format(date)
            } catch (e: Exception) {
                return ""
            }
        }
}

data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)

data class FeelsLike(
    @SerializedName("day")
    val day: Double,
    @SerializedName("eve")
    val eve: Double,
    @SerializedName("morn")
    val morn: Double,
    @SerializedName("night")
    val night: Double
)

data class Temp(
    @SerializedName("day")
    val day: Double,
    @SerializedName("eve")
    val eve: Double,
    @SerializedName("max")
    val max: Double,
    @SerializedName("min")
    val min: Double,
    @SerializedName("morn")
    val morn: Double,
    @SerializedName("night")
    val night: Double
)

class AreaResponse : ArrayList<AreaResponseItem>()

data class AreaResponseItem(
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("local_names")
    val localNames: LocalNames,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String
)

data class LocalNames(
    @SerializedName("ascii")
    val ascii: String,
    @SerializedName("en")
    val en: String,
    @SerializedName("feature_name")
    val featureName: String
)

class CityResponse : ArrayList<CityResponseItem>()

data class CityResponseItem(
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("state")
    val state: String
)