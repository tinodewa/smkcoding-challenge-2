package com.smkcoding.hamurchef.ui.recipe

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smkcoding.hamurchef.R
import com.smkcoding.hamurchef.data.RecipeResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.home_recipe_book_item.*

//class RecipeAdapter(
//    private val context: Context, private val items: List<RecipeResponse>
//    private val listener: (RecipeResponse) -> Unit,
//) :
//    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ) = ViewHolder(
//        context, LayoutInflater.from(context).inflate(
//            R.layout.home_recipe_book_item,
//            parent, false
//        )
//    )
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindItem(items.get(position), listener)
//    }
//
//    class ViewHolder(val context: Context, override val containerView: View) :
//        RecyclerView.ViewHolder(containerView), LayoutContainer {
//        fun bindItem(item: RecipeResponse, listener: (RecipeResponse) -> Unit) {
//
//            Glide.with(context).load(item.results!![3]).into(recipeImage)
//            containerView.setOnClickListener { listener(item) }
//
//            txtRecipeName.text = item.title
//
//
//        }
//    }
//}

//class RecipeAdapter(
//    private val context: Context, private val items: List<RecipeResponse>,
//    private val listener: (RecipeResponse) -> Unit
//) :
//    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ) = ViewHolder(
//        context,
//        LayoutInflater.from(context).inflate(
//            R.layout.home_recipe_book_item,
//            parent, false
//        )
//    )
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindItem(items.get(position), listener)
//    }
//
//    class ViewHolder(val context: Context, override val containerView: View) :
//        RecyclerView.ViewHolder(containerView), LayoutContainer {
//        fun bindItem(item: RecipeResponse, listener: (RecipeResponse) -> Unit) {
//
//            txtRecipeName.text = item.title
//
//            Glide.with(context).load(item.result!![3]).into(recipeImage)
//            containerView.setOnClickListener { listener(item) }
//
//        }
//    }
//}

class RecipeAdapter(
    private val context: Context, private val items: List<RecipeResponse>
) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.home_recipe_book_item,
            parent, false
        )
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position))
    }

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: RecipeResponse) {

//            txtRecipeName.text = item.result[0].toString()
//            Log.d("JSON", item.result[0].toString())
            txtRecipeName.text = item.title

        }
    }
}