package com.example.mycallerapp.adpters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mycallerapp.fragments.CallLogsFragment
import com.example.mycallerapp.fragments.ContactsFragment
import com.example.mycallerapp.fragments.MessagesFragment

class ViewPagerAdapter(var fragmentActivity: FragmentActivity ) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CallLogsFragment()
            1 -> ContactsFragment()
            else -> MessagesFragment()
        }
    }

}