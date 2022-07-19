package com.example.mycolleagueapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypeopleandroomapp.network.PeopleAndRoomRepo
import com.example.mypeopleandroomapp.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomAndPeopleInfoViewModel @Inject constructor(private val repo: PeopleAndRoomRepo) :
    ViewModel() {


    private val roomInfoResponseLiveData = MutableLiveData<ResponseStatus>()
    val roomInfo: LiveData<ResponseStatus> get() = roomInfoResponseLiveData


    private val peopleInfoResponseLiveData = MutableLiveData<ResponseStatus>()
    val peopleInfo: LiveData<ResponseStatus> get() = peopleInfoResponseLiveData

    init {
        getPeopleInfo()
        getRoomInfo()
    }

    private fun getPeopleInfo() {
        viewModelScope.launch {
            repo.getPeopleInfo().collect {
                peopleInfoResponseLiveData.postValue(it)
            }
        }
    }

    private fun getRoomInfo() {
        viewModelScope.launch {
            repo.getRoomInfo().collect {
                roomInfoResponseLiveData.postValue(it)
            }
        }
    }


}