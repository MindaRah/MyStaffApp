package com.example.mypeopleandroomapp.network

import com.example.mypeopleandroomapp.model.People
import com.example.mypeopleandroomapp.model.Room
import com.example.mypeopleandroomapp.utils.Constants.Companion.API_PATH_PEOPLE
import com.example.mypeopleandroomapp.utils.Constants.Companion.API_PATH_ROOMS
import retrofit2.Response
import retrofit2.http.GET

interface APICalls {

    @GET(API_PATH_PEOPLE)
    suspend fun getPeopleInfo(): Response<People>

    @GET(API_PATH_ROOMS)
    suspend fun getRoomInfo(): Response<Room>
}