package com.example.mvvmweather

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import com.example.mvvmweather.di.components.DaggerRestComponent
import com.example.mvvmweather.di.components.RestComponent
import com.example.mvvmweather.di.modules.AppModule
import com.example.mvvmweather.di.modules.NetModule

class ApplicationMain : Application() {

    var restComponent: RestComponent? = null

    override fun onCreate() {
        super.onCreate()

        restComponent = DaggerRestComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(BuildConfig.API_HOST))
            .build()

        restComponent?.inject(this)
    }
}
