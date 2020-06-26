package com.smkcoding.hamurchef

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.smkcoding.hamurchef.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val menuIcon = arrayOf(
        R.drawable.ic_recipe_book_black,
        R.drawable.ic_search_food_black,
        R.drawable.ic_ingredients
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
                    menuIcon[position], null
                )
            }).attach()

        home_vp.canScrollHorizontally(10)

    }
}
