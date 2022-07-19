package com.example.mypeopleandroomapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mypeopleandroomapp.R
import com.example.mypeopleandroomapp.view.RoomFragment
import com.example.mypeopleandroomapp.view.StaffFragment

class ViewStaffAndRoomAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val TABS= arrayListOf(R.string.Staff,R.string.Rooms)
    override fun getCount(): Int {
        return 2
    }
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> StaffFragment()
            1 -> RoomFragment()
            else -> {
                StaffFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TABS[position])
    }
}