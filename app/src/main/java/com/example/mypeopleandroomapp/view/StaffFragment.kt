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
import com.example.mypeopleandroomapp.databinding.FragmentStaffBinding
import com.example.mypeopleandroomapp.model.PeopleItem
import com.example.mypeopleandroomapp.utils.ResponseStatus

private const val FIRST_PAR = "first_par"
private const val SECOND_PAR = "second_par"

class StaffFragment : FraInfoViewModel() {

    private var first_par: String? = null
    private var second_par: String? = null

    lateinit var staffAdapter: PeopleAndRoomAdapter

    private val staffBinding: FragmentStaffBinding by lazy {
        FragmentStaffBinding.inflate(layoutInflater)
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
        staffAdapter = PeopleAndRoomAdapter(desPeople = ::openStaffDialog)
        staffBinding.staffInfoRecyclerView.adapter = staffAdapter
        setUpObserver()
        return staffBinding.root
    }

private fun setUpObserver() {
    viewModel.peopleInfo.observe(viewLifecycleOwner) {uiState ->
        when (uiState) {
            is ResponseStatus.SUCCESS<*> -> {
                staffBinding.apply {
                    progressBar.visibility = View.GONE
                    val staff = uiState.response
                    staffAdapter.changePeopleList(staff as List<PeopleItem>)
                }
            }
            is ResponseStatus.ERROR -> {
                staffBinding.apply {
                    progressBar.visibility = View.GONE
                    errorTextView.text = uiState.error.message
                }
            }
            else -> {}
        }
    }
}

@SuppressLint("ChangeText")
fun openStaffDialog(peopleItem: PeopleItem) {
    val warningDialog: AlertDialog
    val make: AlertDialog.Builder = AlertDialog.Builder(requireContext())
    val staffView = layoutInflater.inflate(R.layout.fragment_staff_details, null)

    val image : ImageView = staffView.findViewById(R.id.imageView)
    val txtColor: TextView = staffView.findViewById(R.id.tvDetailedColor)
    val txtName: TextView = staffView.findViewById(R.id.tvDetailedName)
    val txtJob: TextView = staffView.findViewById(R.id.tvDetailedJobTitle)
    val txtEmail: TextView = staffView.findViewById(R.id.tvDetailedEmail)
    val colorImage :ImageView = staffView.findViewById(R.id.colorView)

    txtColor.text = peopleItem.favouriteColor

    Glide.with(requireActivity()).load(peopleItem.avatar).into(image)
    txtEmail.text = peopleItem.email
    txtJob.text = peopleItem.jobtitle
    txtName.text = peopleItem.firstName + " " + peopleItem.lastName

    make.setView(staffView)
    warningDialog = make.create()
    warningDialog.show()
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
