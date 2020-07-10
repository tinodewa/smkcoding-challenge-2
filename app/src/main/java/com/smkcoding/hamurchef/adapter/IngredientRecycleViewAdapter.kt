package com.smkcoding.hamurchef.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smkcoding.hamurchef.R
import com.smkcoding.hamurchef.data.Ingredient
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.ingredient_item.*

class IngredientRecycleViewAdapter(
    private val context: Context, private val items: List<Ingredient>,
    private val listener: (Ingredient) -> Unit
) :
    RecyclerView.Adapter<IngredientRecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        context,
        LayoutInflater.from(context).inflate(
            R.layout.ingredient_item,
            parent, false
        )
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: Ingredient, listener: (Ingredient) -> Unit) {

            txtIngredient.text = item.strIngredient

            containerView.setOnClickListener { listener(item) }

        }
    }
}
