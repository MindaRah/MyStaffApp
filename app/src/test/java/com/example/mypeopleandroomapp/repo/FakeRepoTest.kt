package com.example.mypeopleandroomapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.mypeopleandroomapp.model.People
import com.example.mypeopleandroomapp.network.PeopleAndRoomRepo
import com.example.mypeopleandroomapp.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepoTest: PeopleAndRoomRepo {
    val roomLiveInfo = MutableLiveData<ResponseStatus>()
    private val staffItem = mutableListOf<People>()
    val staffLiveInfo = MutableLiveData<ResponseStatus>()

    var returnNNetworkErrorMsg = false

    fun changeReturnNetworkErrorMsg(result: Boolean) {
        changeReturnNetworkErrorMsg(result)
    }
    override suspend fun getRoomInfo(): Flow<ResponseStatus> =
        flow {

        }
    override suspend fun getPeopleInfo(): Flow<ResponseStatus> =
        flow {
        }

}