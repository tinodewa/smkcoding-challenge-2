package com.smkcoding.hamurchef.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smkcoding.hamurchef.ui.Search.SearchFragment
import com.smkcoding.hamurchef.ui.recipe.RecipeFragment
import com.smkcoding.hamurchef.ui.profile.ProfileFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val JUMLAH_MENU = 3

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return RecipeFragment()
            }
            1 -> {
                return SearchFragment()
            }
            2 -> {
                return ProfileFragment()
            }
            else -> {
                return SearchFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }

}