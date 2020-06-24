package com.smkcoding.hamurchef.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smkcoding.hamurchef.R
import com.smkcoding.hamurchef.data.Detail
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.home_recipe_book_item.*

class RecipeRecycleViewAdapter(
    private val context: Context, private val items: List<Detail>,
    private val listener: (Detail) -> Unit
) :
    RecyclerView.Adapter<RecipeRecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        context,
        LayoutInflater.from(context).inflate(
            R.layout.home_recipe_book_item,
            parent, false
        )
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: Detail, listener: (Detail) -> Unit) {

            txtRecipeName.text = item.strMeal
            txtRecipeCountry.text = item.strArea

            Glide.with(context).load(item.strMealThumb).into(recipeImage)
            containerView.setOnClickListener { listener(item) }

        }
    }
}
