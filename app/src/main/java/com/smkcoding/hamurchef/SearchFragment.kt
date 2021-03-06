package com.smkcoding.hamurchef

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.hamurchef.adapter.RecipeRecycleViewAdapter
import com.smkcoding.hamurchef.data.*
import com.smkcoding.hamurchef.data.recipe.RecipeData
import com.smkcoding.hamurchef.data.recipe.Recipe
import com.smkcoding.hamurchef.utils.dismissLoading
import com.smkcoding.hamurchef.utils.hideKeyboard
import com.smkcoding.hamurchef.utils.showLoading
import com.smkcoding.hamurchef.utils.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private var searchText : String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetRecipe()

        search_btn.setOnClickListener {
            searchText = search_txt.text.toString()
            callSearchedRecipe(searchText)
            hideKeyboard()
        }
    }

    private fun callSearchedRecipe(searchtext: String) {

        showLoading(requireContext(), search_srl)

        val httpClient = httpClient()
        val apiRequest = apiRequest<RecipeService>(httpClient)

        val call = apiRequest.getIngredient(searchtext)
        call.enqueue(object : Callback<Recipe> {

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                dismissLoading(search_srl)
            }

            override fun onResponse(
                call: Call<Recipe>,
                response: Response<Recipe>
            ) {
                dismissLoading(search_srl)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.meals?.size != 0
                            ->
                                showRecipe(response.body()!!.meals)
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

    private fun callApiGetRecipe() {
        showLoading(requireContext(), search_srl)

        val httpClient = httpClient()
        val apiRequest = apiRequest<RecipeService>(httpClient)

        val call = apiRequest.getRecipes()
        call.enqueue(object : Callback<Recipe> {

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                dismissLoading(search_srl)
            }

            override fun onResponse(
                call: Call<Recipe>,
                response: Response<Recipe>
            ) {
                dismissLoading(search_srl)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.meals?.size != 0
                            ->
                                showRecipe(response.body()!!.meals)
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

    private fun showRecipe(result: List<RecipeData>) {
        rv_searchRecipeBook.layoutManager = LinearLayoutManager(context)
        rv_searchRecipeBook.adapter =
            RecipeRecycleViewAdapter(requireContext(), result) {
                val meal = it
//                tampilToast(requireContext(), meal.strMeal)
                tampilToast(requireContext(), meal.strMeal)
                val intent = Intent(requireContext(), RecipeDetails::class.java)
                val bundle = Bundle()
                bundle.putString("mealName", meal.strMeal)
                bundle.putString("mealTags", meal.strTags)
                bundle.putString("mealThumb", meal.strMealThumb)
                intent.putExtras(bundle)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}


