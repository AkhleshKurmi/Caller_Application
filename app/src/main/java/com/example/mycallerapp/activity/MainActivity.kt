package com.example.mycallerapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mycallerapp.R
import com.example.mycallerapp.adpters.ViewPagerAdapter
import com.example.mycallerapp.databinding.ActivityMainBinding
import com.example.mycallerapp.factory.Factory
import com.example.mycallerapp.viewmodel.CallerViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel:CallerViewModel
    lateinit var fragmentAdapter: ViewPagerAdapter
    private var tabNames = arrayOf("Call Logs", "Contacts", "Messages")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        viewModel = ViewModelProvider(this,Factory(this))[CallerViewModel::class.java]

        fragmentAdapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = fragmentAdapter
        TabLayoutMediator(binding.tabLayot,binding.viewPager){tab , position ->
            tab.text = tabNames[position]
        }.attach()


    }
//     var contactsList = ArrayList<ContactItems>()





}