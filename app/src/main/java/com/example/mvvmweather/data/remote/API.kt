package com.example.mvvmweather.data.remote


import com.example.mvvmweather.data.models.ApiResponse
import com.example.mvvmweather.data.models.AreaResponse
import com.example.mvvmweather.data.models.CityResponse
import retrofit2.Call
import retrofit2.http.*


/**
 * Developed by Hasham.Tahir on 11/22/17.
 */

object API {

    const val ACTION_WEATHER = "data/2.5/onecall?"
    const val ACTION_AREA = "geo/1.0/reverse?"
    const val ACTION_CITY = "geo/1.0/direct?"

    interface Endpoints {

        @GET(ACTION_WEATHER)
        fun getWeatherForLocation(
            @Query("lat") lat: String,
            @Query("lon") lon: String,
            @Query("appid") appid: String, @Query("units") units: String = "metric"
        ): Call<ApiResponse>

        @GET(ACTION_CITY)
        fun getWeatherForCity(
            @Query("q") cityName: String,
            @Query("appid") appid: String,
            @Query("limit") units: String = "2"
        ): Call<CityResponse>

        @GET(ACTION_AREA)
        fun getArea(
            @Query("lat") lat: String,
            @Query("lon") lon: String,
            @Query("appid") appid: String, @Query("units") units: String = "metric"
        ): Call<AreaResponse>

    }

}
