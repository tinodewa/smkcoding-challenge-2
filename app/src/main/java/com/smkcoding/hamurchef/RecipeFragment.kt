package com.smkcoding.hamurchef

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.hamurchef.adapter.RecipeRecycleViewAdapter
import com.smkcoding.hamurchef.data.*
import com.smkcoding.hamurchef.data.recipe.RecipeData
import com.smkcoding.hamurchef.data.recipe.Recipe
import com.smkcoding.hamurchef.utils.dismissLoading
import com.smkcoding.hamurchef.utils.showLoading
import com.smkcoding.hamurchef.utils.tampilToast
import com.smkcoding.hamurchef.viewmodel.MyRecipeFragmentViewModel
//import com.smkcoding.hamurchef.viewmodel.MyRecipeFragmentViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_recipe.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeFragment : Fragment() {

    var dataRecipe: MutableList<RecipeData> = ArrayList()
    private val viewModel by viewModels<MyRecipeFragmentViewModel>()
    private var adapter: RecipeData? = null

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
        init()
        callApiGetRecipe()
        viewModel.init(requireContext())
        viewModel.allMyRecipes.observe(viewLifecycleOwner, Observer { myRecipes ->
            // Update the cached copy of the words in the adapter.
            myRecipes?.let { adapter }
        })
    }

    private fun init() {
        rv_listRecipeBook.layoutManager = LinearLayoutManager(context)
        rv_listRecipeBook.adapter = RecipeRecycleViewAdapter(requireContext(), dataRecipe) {
            val recipeFood = it
            tampilToast(requireContext(), recipeFood.strMeal)
            val intent = Intent(requireContext(), RecipeDetails::class.java)
            val bundle = Bundle()
            bundle.putString("mealName", recipeFood.strMeal)
            bundle.putString("mealTags", recipeFood.strTags)
            bundle.putString("mealThumb", recipeFood.strMealThumb)
            intent.putExtras(bundle)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    private fun callApiGetRecipe() {
        showLoading(requireContext(), recipe_srl)

        val httpClient = httpClient()
        val apiRequest = apiRequest<RecipeService>(httpClient)

        val call = apiRequest.getRecipes()
        call.enqueue(object : Callback<Recipe> {

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                dismissLoading(recipe_srl)
            }

            override fun onResponse(
                call: Call<Recipe>,
                response: Response<Recipe>
            ) {
                dismissLoading(recipe_srl)

                when {
                    response.isSuccessful ->
//                        when {
//                            response.body()?.meals?.size != 0
//                            ->
//                                showRecipe(response.body()!!.meals)
//                            else -> {
//                                tampilToast(context!!, "Berhasil")
//                            }
//                        }

                        showRecipe(response.body()!!.meals)
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

    private fun showRecipe(result: List<RecipeData>) {
        rv_listRecipeBook.layoutManager = LinearLayoutManager(context)
        rv_listRecipeBook.adapter = RecipeRecycleViewAdapter(requireContext(), result) {
            val recipeFood = it
            tampilToast(requireContext(), recipeFood.strMeal)
            val intent = Intent(requireContext(), RecipeDetails::class.java)
            val bundle = Bundle()
            bundle.putString("mealName", recipeFood.strMeal)
            bundle.putString("mealTags", recipeFood.strTags)
            bundle.putString("mealThumb", recipeFood.strMealThumb)
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
