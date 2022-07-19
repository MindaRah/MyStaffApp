package com.example.mypeopleandroomapp.di

import com.example.mypeopleandroomapp.network.APICalls
import com.example.mypeopleandroomapp.network.PeopleAndRoomImp
import com.example.mypeopleandroomapp.network.PeopleAndRoomRepo
import com.example.mypeopleandroomapp.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
//defining feature
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun getRetrofit(): APICalls =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
            .create(APICalls::class.java)

    @Provides
    @Singleton
    fun getPeopleAndRoomRepository(): PeopleAndRoomRepo {
        return PeopleAndRoomImp(getRetrofit())
    }

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}