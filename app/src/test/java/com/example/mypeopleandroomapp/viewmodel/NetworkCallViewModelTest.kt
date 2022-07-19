package com.example.mypeopleandroomapp.viewmodel

import androidx.lifecycle.Observer
import com.example.mycolleagueapp.viewmodel.RoomAndPeopleInfoViewModel
import com.example.mypeopleandroomapp.model.People
import com.example.mypeopleandroomapp.model.PeopleItem
import com.example.mypeopleandroomapp.network.APICalls
import com.example.mypeopleandroomapp.network.PeopleAndRoomRepo
import com.example.mypeopleandroomapp.utils.ResponseStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NetworkCallViewModelTest {

    @get:Rule
    val coroutineRuleT = CoroutineRule()

    @Before
    fun conFig() {
    }

    @Mock
    private lateinit var apiCall: APICalls

    @Mock
    private lateinit var apiPeopleObserver: Observer<ResponseStatus.SUCCESS<List<PeopleItem>>>

    @Mock
    lateinit var repo: PeopleAndRoomRepo

    @Test
    fun providedServerReplyWhengettingSuccessIsReturned() {
        coroutineRuleT.runBlockingTest {
            doReturn(emptyList<People>())
                .`when`(apiCall)
                .getPeopleInfo()
            val peopleViewModel = RoomAndPeopleInfoViewModel(repo = repo)
            peopleViewModel.peopleInfo.fetchOrAwait()
            verify(apiCall)
            verify(apiPeopleObserver).onChanged(ResponseStatus.SUCCESS(emptyList()))
            peopleViewModel.peopleInfo
        }
    }
}