package com.example.mvvmweather.ui

import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmweather.data.remote.API
import com.google.gson.Gson
import javax.inject.Inject

class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: API.Endpoints

    @Inject
    lateinit var gson: Gson
}