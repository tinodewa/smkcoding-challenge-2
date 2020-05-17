package com.smkcoding.hamurchef.ui.ingredient

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.hamurchef.R
import com.smkcoding.hamurchef.adapter.IngredientRecycleViewAdapter
import com.smkcoding.hamurchef.adapter.RecipeRecycleViewAdapter
import com.smkcoding.hamurchef.data.*
import com.smkcoding.hamurchef.utils.dismissLoading
import com.smkcoding.hamurchef.utils.showLoading
import com.smkcoding.hamurchef.utils.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_ingredient.*
import kotlinx.android.synthetic.main.fragment_recipe.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngredientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ingredient,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetIngredient()
    }

    private fun callApiGetIngredient() {
        showLoading(context!!, ingredient_srl)

        val httpClient = httpClient()
        val apiRequest = apiRequest<RecipeService>(httpClient)

        val call = apiRequest.getMainIngredients()
        call.enqueue(object : Callback<IngredientResponse> {

            override fun onFailure(call: Call<IngredientResponse>, t: Throwable) {
                dismissLoading(ingredient_srl)
            }

            override fun onResponse(
                call: Call<IngredientResponse>,
                response: Response<IngredientResponse>
            ) {
                dismissLoading(ingredient_srl)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.meals?.size != 0
                            ->
                                response.body()!!.meals?.let { showRecipe(it) }
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }

                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

    private fun showRecipe(result: List<Ingredient>) {
        rv_listIngredient.layoutManager = LinearLayoutManager(context)
        rv_listIngredient.adapter =
            IngredientRecycleViewAdapter(context!!, result)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
