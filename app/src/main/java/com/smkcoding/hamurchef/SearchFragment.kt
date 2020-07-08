package com.smkcoding.hamurchef

import android.os.Bundle
import android.view.*
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.hamurchef.adapter.RecipeRecycleViewAdapter
import com.smkcoding.hamurchef.data.*
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

        search_btn.setOnClickListener {v ->
            searchText = search_txt.text.toString()
            callSearchedRecipe(searchText)
            hideKeyboard()
        }
    }

    private fun callSearchedRecipe(searchtext: String) {

        showLoading(context!!, search_srl)

        val httpClient = httpClient()
        val apiRequest = apiRequest<RecipeService>(httpClient)

        val call = apiRequest.getIngredient(searchtext)
        call.enqueue(object : Callback<RecipeResponse> {

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                dismissLoading(search_srl)
            }

            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                dismissLoading(search_srl)

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

    private fun callApiGetRecipe() {
        showLoading(context!!, search_srl)

        val httpClient = httpClient()
        val apiRequest = apiRequest<RecipeService>(httpClient)

        val call = apiRequest.getRecipes()
        call.enqueue(object : Callback<RecipeResponse> {

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                dismissLoading(search_srl)
            }

            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                dismissLoading(search_srl)

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

    private fun showRecipe(result: List<Detail>) {
        rv_searchRecipeBook.layoutManager = LinearLayoutManager(context)
        rv_searchRecipeBook.adapter =
            RecipeRecycleViewAdapter(context!!, result) {
                val meal = it
                tampilToast(context!!, meal.strMeal)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}


