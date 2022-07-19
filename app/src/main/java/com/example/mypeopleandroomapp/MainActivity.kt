package com.example.mypeopleandroomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.mypeopleandroomapp.adapter.ViewPagerAdapter
import com.example.mypeopleandroomapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var partPagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        partPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        val mainViewPager: ViewPager = viewBinding.mainViewPager
        mainViewPager.adapter = partPagerAdapter
        val tabLayout: TabLayout = viewBinding.tabLayout
        tabLayout.setupWithViewPager(mainViewPager)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_house)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_staff)



    }
}