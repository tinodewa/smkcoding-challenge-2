package com.smkcoding.hamurchef.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smkcoding.hamurchef.SearchFragment
import com.smkcoding.hamurchef.RecipeFragment
import com.smkcoding.hamurchef.IngredientFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val JUMLAH_MENU = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                RecipeFragment()
            }
            1 -> {
                SearchFragment()
            }
            2 -> {
                IngredientFragment()
            }
            else -> {
                SearchFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }

}