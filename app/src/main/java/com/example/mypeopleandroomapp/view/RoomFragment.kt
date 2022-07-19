package com.example.mypeopleandroomapp.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mycolleagueapp.viewmodel.FraInfoViewModel
import com.example.mypeopleandroomapp.R
import com.example.mypeopleandroomapp.adapter.PeopleAndRoomAdapter
import com.example.mypeopleandroomapp.databinding.FragmentRoomBinding
import com.example.mypeopleandroomapp.databinding.FragmentStaffBinding
import com.example.mypeopleandroomapp.model.PeopleItem
import com.example.mypeopleandroomapp.model.RoomItem
import com.example.mypeopleandroomapp.utils.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint

private const val FIRST_PAR = "first_par"
private const val SECOND_PAR = "second_par"


class RoomFragment : FraInfoViewModel() {

    private var first_par: String? = null
    private var second_par: String? = null

    lateinit var roomAdapter: PeopleAndRoomAdapter

    private val roomBinding: FragmentRoomBinding by lazy {
        FragmentRoomBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            first_par = it.getString(FIRST_PAR)
            second_par = it.getString(SECOND_PAR)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        roomAdapter = PeopleAndRoomAdapter(desPeople = ::openRoomDialog)
        roomBinding.roomInfoRecyclerView.adapter = roomAdapter
        setUpObserver()
        return roomBinding.root
    }


    private fun setUpObserver() {
        viewModel.roomInfo.observe(viewLifecycleOwner) {uiState ->
            when (uiState) {
                is ResponseStatus.SUCCESS<*> -> {
                    roomBinding.apply {
                        RoomProgressbar.visibility = View.GONE
                        val room = uiState.response
                        roomAdapter.changeRoomList(room as List<RoomItem>)
                    }
                }
                is ResponseStatus.ERROR -> {
                    roomBinding.apply {
                        RoomProgressbar.visibility = View.GONE
                        errorTextView.text = uiState.error.message
                    }
                }
                else -> {}
            }
        }
    }

    fun openRoomDialog(roomItem: PeopleItem) {
    }
    companion object {
        fun newObject(firstPar: String, secondPar: String) =
            StaffFragment().apply {
                arguments = Bundle().apply {
                    putString(FIRST_PAR, firstPar)
                    putString(SECOND_PAR, secondPar)
                }
            }
    }

}
