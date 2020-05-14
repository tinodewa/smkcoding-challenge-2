package com.smkcoding.hamurchef

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.smkcoding.hamurchef.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    val menuIconOnRender = arrayOf(
        R.drawable.ic_recipe_book_colored,
        R.drawable.ic_search_food_black,
        R.drawable.ic_profile_man_black
    )

    val menuIconUnselected = arrayOf(
        R.drawable.ic_recipe_book_black,
        R.drawable.ic_search_food_black,
        R.drawable.ic_profile_man_black
    )

    val menuIconSelected = arrayOf(
        R.drawable.ic_recipe_book_colored,
        R.drawable.ic_search_food_colored,
        R.drawable.ic_profile_man_colored
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val adapter = ViewPagerAdapter(this)
        home_vp.setAdapter(adapter);
        TabLayoutMediator(home_tl, home_vp,
            TabConfigurationStrategy { tab, position ->
                tab.icon = ResourcesCompat.getDrawable(
                    resources,
                    menuIconOnRender[position], null
                )
            }).attach()

        home_tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                home_vp.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                home_vp.currentItem = tab!!.position
                tab.setIcon(ResourcesCompat.getDrawable(
                    resources,
                    menuIconUnselected[tab.position], null
                ))
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                home_vp.currentItem = tab!!.position
                tab.setIcon(ResourcesCompat.getDrawable(
                    resources,
                    menuIconSelected[tab.position], null
                ))
            }

        })
    }
}
