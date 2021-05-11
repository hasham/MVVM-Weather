package com.example.mvvmweather.ui.main

import android.app.Application
import android.location.Location
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.mvvmweather.ApplicationMain
import com.example.mvvmweather.data.remote.API
import com.example.mvvmweather.data.repository.BaseRepository
import com.example.mvvmweather.ui.BaseNavigator
import com.example.mvvmweather.ui.NotifyType
import javax.inject.Inject

class MainViewModel(
    application: Application,
    private val navigator: BaseNavigator,
    owner: LifecycleOwner
) : AndroidViewModel(application) {

    @Inject
    lateinit var apiService: API.Endpoints
    private var baseRepository: BaseRepository
    private var owner: LifecycleOwner
    val showLoadingView = ObservableBoolean(false)
    val locationObserver = MutableLiveData<Location?>().apply { postValue(null) }

    init {
        (application as ApplicationMain).restComponent?.inject(this)
        baseRepository = BaseRepository(apiService, getApplication())
        this.owner = owner

        locationObserver.observe(owner, { location ->

            if (location != null) {
                getWeatherForLocation(location.latitude.toString(), location.longitude.toString())
                getArea(location.latitude.toString(), location.longitude.toString())
            }
        })
    }

    private fun getWeatherForLocation(lat: String, long: String) {

        val observable = baseRepository.getWeatherForLocation(lat, long)

        showLoadingView.set(true)

        observable.observe(owner, { response ->

            if (response != null) {
                navigator.onWeatherResponse(response)
            } else {
                navigator.notifyUser("Unable to get weather", NotifyType.TYPE_TOAST)
            }

            showLoadingView.set(false)
        })
    }

    fun getWeatherForCityName(cityName: String) {

        val observable = baseRepository.getWeatherForCity(cityName)
        observable.observe(owner, { response ->

            if (response != null) {
                getWeatherForLocation(response[0].lat.toString(), response[0].lon.toString())
                getArea(response[0].lat.toString(), response[0].lon.toString())
            } else {
                navigator.notifyUser("Unable to get weather", NotifyType.TYPE_TOAST)
            }
        })
    }


    private fun getArea(lat: String, long: String) {

        val observable = baseRepository.getArea(lat, long)
        observable.observe(owner, { response ->

            if (response != null) {
                navigator.onAreaResponse(response)
            } else {
                navigator.notifyUser("Unable to get Area", NotifyType.TYPE_TOAST)
            }
        })
    }

}