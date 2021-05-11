package com.example.mvvmweather.di.components

import com.example.mvvmweather.ApplicationMain
import com.example.mvvmweather.di.modules.AppModule
import com.example.mvvmweather.di.modules.NetModule
import com.example.mvvmweather.ui.BaseActivity
import com.example.mvvmweather.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Developed by Hasham.Tahir on 1/5/2017.
 */

@Singleton
@Component(modules = [(AppModule::class), (NetModule::class)])
interface RestComponent {
    fun inject(application: ApplicationMain)
    fun inject(activity: BaseActivity)
    fun inject(viewModel: MainViewModel)
}