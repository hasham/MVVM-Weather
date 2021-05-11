package com.example.mvvmweather.ui

interface BaseNavigator {
    fun notifyUser(text: String, type: NotifyType)
    fun onNavigateBack(className: String?)
    fun onNavigateForward(className: String?)
    fun onWeatherResponse(obj: Any?)
    fun onAreaResponse(obj: Any?)
}

enum class NotifyType {
    TYPE_TOAST,
    TYPE_ALERT,
    TYPE_SNACKBAR
}