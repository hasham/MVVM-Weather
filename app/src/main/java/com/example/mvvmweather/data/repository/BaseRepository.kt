package com.example.mvvmweather.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmweather.ApplicationMain
import com.example.mvvmweather.BuildConfig
import com.example.mvvmweather.data.models.ApiResponse
import com.example.mvvmweather.data.models.AreaResponse
import com.example.mvvmweather.data.models.CityResponse
import com.example.mvvmweather.data.remote.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Developed by hasham on 4/3/19.
 */
open class BaseRepository(
    private val apiService: API.Endpoints,
    private val application: ApplicationMain
) {

    fun getWeatherForLocation(lat: String, lon: String): LiveData<ApiResponse> {

        val data = MutableLiveData<ApiResponse>()

        apiService.getWeatherForLocation(lat, lon, BuildConfig.API_KEY).enqueue(object :
            Callback<ApiResponse> {

            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {

                data.value = null
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                data.value = response.body()
            }
        })

        return data

    }

    fun getWeatherForCity(cityName: String): LiveData<CityResponse> {

        val data = MutableLiveData<CityResponse>()

        apiService.getWeatherForCity(cityName, BuildConfig.API_KEY).enqueue(object :
            Callback<CityResponse> {

            override fun onFailure(call: Call<CityResponse>?, t: Throwable?) {

                data.value = null
            }

            override fun onResponse(call: Call<CityResponse>, response: Response<CityResponse>) {

                data.value = response.body()
            }
        })

        return data

    }

    fun getArea(lat: String, lon: String): LiveData<AreaResponse> {

        val data = MutableLiveData<AreaResponse>()

        apiService.getArea(lat, lon, BuildConfig.API_KEY).enqueue(object :
            Callback<AreaResponse> {

            override fun onFailure(call: Call<AreaResponse>?, t: Throwable?) {

                data.value = null
            }

            override fun onResponse(call: Call<AreaResponse>, response: Response<AreaResponse>) {

                data.value = response.body()
            }
        })

        return data

    }


}