package com.smkcoding.hamurchef.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smkcoding.hamurchef.ui.dashboard.DashboardFragment
import com.smkcoding.hamurchef.ui.home.HomeFragment
import com.smkcoding.hamurchef.ui.notifications.NotificationsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val JUMLAH_MENU = 3

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return DashboardFragment()
            }
            2 -> {
                return NotificationsFragment()
            }
            else -> {
                return DashboardFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }

}