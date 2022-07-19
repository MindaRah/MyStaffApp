package com.example.mycolleagueapp.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class FraInfoViewModel: Fragment() {
    protected val viewModel: RoomAndPeopleInfoViewModel by activityViewModels()
}