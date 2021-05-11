package com.example.mvvmweather.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mvvmweather.data.models.Weather

@BindingAdapter("weatherIcon")
fun setImageExternalFresco(imageView: ImageView, weather: List<Weather>?) {
    if (weather != null && weather.isNotEmpty()) {

        val url = String.format("https://openweathermap.org/img/w/%s.png", weather[0].icon);
        Glide.with(imageView.context.applicationContext)
            .load(url)
            .into(imageView);
    }
}