package com.example.mvvmweather.di.modules

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.example.mvvmweather.ApplicationMain
import com.example.mvvmweather.data.remote.API
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Developed by Hasham.Tahir on 1/5/2017.
 */

@Module
class NetModule
@Inject
constructor(private val mBaseUrl: String) {

    lateinit var application: ApplicationMain

    // Dagger will only look for methods annotated with @Provides

    @Provides
    @Singleton
    internal fun providesApiEndpoints(retrofit: Retrofit): API.Endpoints =
            retrofit.create<API.Endpoints>(API.Endpoints::class.java)


    @Provides
    @Singleton
    internal fun provideOkHttpCache(application: Application): Cache {
        this.application = application as ApplicationMain
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }
    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
//        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
//        }
        httpClient.readTimeout(2, TimeUnit.MINUTES)
        httpClient.connectTimeout(2, TimeUnit.MINUTES)
        httpClient.writeTimeout(2, TimeUnit.MINUTES)
        httpClient.cache(cache)

        httpClient.addInterceptor { chain ->
            val request = chain.request()
                    .newBuilder()
//                    .header("User-Agent", "Android")
//                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build()

            chain.proceed(request)
        }

        return httpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build()
    }
}