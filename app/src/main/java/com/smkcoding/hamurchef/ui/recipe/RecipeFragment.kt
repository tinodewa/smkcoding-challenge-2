package com.smkcoding.hamurchef.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.hamurchef.R
import com.smkcoding.hamurchef.data.*
import com.smkcoding.hamurchef.utils.dismissLoading
import com.smkcoding.hamurchef.utils.showLoading
import com.smkcoding.hamurchef.utils.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_recipe.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class   RecipeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetRecipe()
    }

    private fun callApiGetRecipe() {
        showLoading(context!!, recipe_srl)

        val httpClient = httpClient()
        val apiRequest = apiRequest<RecipeService>(httpClient)

        val call = apiRequest.getRecipes()
        call.enqueue(object : Callback<RecipeResponse> {

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                dismissLoading(recipe_srl)
            }

            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                dismissLoading(recipe_srl)

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

    private fun showRecipe(result: List<Meal>) {
        rv_listRecipeBook.layoutManager = LinearLayoutManager(context)
        rv_listRecipeBook.adapter = RecipeAdapter(context!!, result){
            val recipeFood = it
            tampilToast(context!!, recipeFood.strMeal)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
