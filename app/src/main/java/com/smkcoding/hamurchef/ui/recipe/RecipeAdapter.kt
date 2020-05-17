package com.smkcoding.hamurchef.ui.recipe

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.smkcoding.hamurchef.R
import com.smkcoding.hamurchef.data.Recipe
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.home_recipe_book_item.*

class RecipeAdapter(
    private val context: Context, private val items: List<Recipe>,
    private val listener: (Recipe) -> Unit
) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

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
        fun bindItem(item: Recipe, listener: (Recipe) -> Unit) {

            txtRecipeName.text = item.title

            Glide.with(context).load(item.thumbnail).into(recipeImage)
            containerView.setOnClickListener { listener(item) }

        }
    }
}
