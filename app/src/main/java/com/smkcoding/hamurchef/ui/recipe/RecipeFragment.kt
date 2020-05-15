package com.smkcoding.hamurchef.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.hamurchef.R
import com.smkcoding.hamurchef.data.RecipeData
import com.smkcoding.hamurchef.data.RecipeService
import com.smkcoding.hamurchef.data.apiRequest
import com.smkcoding.hamurchef.data.httpClient
import com.smkcoding.hamurchef.utils.dismissLoading
import com.smkcoding.hamurchef.utils.showLoading
import com.smkcoding.hamurchef.utils.tampilToast
import kotlinx.android.synthetic.main.fragment_recipe.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeFragment : Fragment() {

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
        showLoading(context!!, home_srl)

        val httpClient = httpClient()
        val apiRequest = apiRequest<RecipeService>(httpClient)

        val call = apiRequest.getRecipes()


        call.enqueue(object : Callback<List<RecipeData>> {

            override fun onFailure(call: Call<List<RecipeData>>, t: Throwable) {
                dismissLoading(home_srl)
            }

            override fun onResponse(
                call: Call<List<RecipeData>>,
                response: Response<List<RecipeData>>
            ) {
                dismissLoading(home_srl)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0
                            ->
                                showRecipe(response.body()!!)



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

    private fun showRecipe(recipes: List<RecipeData>) {
        rv_listRecipeBook.layoutManager = LinearLayoutManager(context)
        rv_listRecipeBook.adapter = RecipeAdapter(context!!, recipes)
    }

//    private fun dataRecipeBook() {
//        recipeBook = ArrayList()
//        recipeBook.add(RecipeBook("Gado-gado","https://kprofiles.com/wp-content/uploads/2019/11/D6aGkQlUcAAgf_n-533x800.jpg"))
//        recipeBook.add(RecipeBook("Rujak","https://kprofiles.com/wp-content/uploads/2019/11/D6aGkQlUcAAgf_n-533x800.jpg"))
//    }
//
//    private fun showRecipeBook() {
//        rv_listRecipeBook.layoutManager = LinearLayoutManager(activity)
//        rv_listRecipeBook.adapter =
//            RecipeAdapter(activity!!, recipeBook) {
//                val recipe = it
//                tampilToast(activity!!, recipe.RecipeName)
//            }
//    }
//
//    private fun initView() {
//        dataRecipeBook()
//        showRecipeBook()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        this.clearFindViewByIdCache()
//    }
}
