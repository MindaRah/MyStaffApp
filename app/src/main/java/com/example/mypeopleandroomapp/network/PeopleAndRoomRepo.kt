package com.example.mypeopleandroomapp.network

import com.example.mypeopleandroomapp.utils.NullResponseException
import com.example.mypeopleandroomapp.utils.ResponseIsAFailure
import com.example.mypeopleandroomapp.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PeopleAndRoomRepo {
    suspend fun getRoomInfo(): Flow<ResponseStatus>
    suspend fun getPeopleInfo(): Flow<ResponseStatus>
}

class PeopleAndRoomImp @Inject constructor(private val apiCalls: APICalls): PeopleAndRoomRepo
{
    override suspend fun getPeopleInfo(): Flow<ResponseStatus> =
        flow {
            emit(ResponseStatus.LOADING)
            try {
                val response = apiCalls.getPeopleInfo()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResponseStatus.SUCCESS(it))
                    } ?: throw Exception("null")
                } else {
                    throw Exception("null ")
                }
            } catch (e: Exception) {
                emit(ResponseStatus.ERROR(e))
            }
        }


    override suspend fun getRoomInfo(): Flow<ResponseStatus> =
        flow {
            emit(ResponseStatus.LOADING)
            try {
                val response = apiCalls.getRoomInfo()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResponseStatus.SUCCESS(it))
                    } ?: throw NullResponseException()
                } else {
                    throw ResponseIsAFailure()
                }
            } catch (e: Exception) {
                emit(ResponseStatus.ERROR(e))
            }
        }
    }


