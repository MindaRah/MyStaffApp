package com.example.mypeopleandroomapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mypeopleandroomapp.R
import com.example.mypeopleandroomapp.view.RoomFragment
import com.example.mypeopleandroomapp.view.StaffFragment

class ViewPagerAdapter(private val vPagCon: Context, fraMan: FragmentManager): FragmentPagerAdapter(fraMan) {

    private val TABS = arrayListOf(R.string.Rooms,R.string.Staff)

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> RoomFragment()
            1 -> StaffFragment()
            else -> {
                RoomFragment()
            }
        }
    }


    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return vPagCon.resources.getString(TABS[position])
    }
}