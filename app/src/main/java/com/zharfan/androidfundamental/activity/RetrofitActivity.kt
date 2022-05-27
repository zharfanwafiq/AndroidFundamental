package com.zharfan.androidfundamental.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.zharfan.androidfundamental.adapter.ReviewAdapter
import com.zharfan.androidfundamental.data.api.ApiConfig
import com.zharfan.androidfundamental.data.response.CustomerReviewsItem
import com.zharfan.androidfundamental.data.response.PostReviewResponse
import com.zharfan.androidfundamental.data.response.Restaurant
import com.zharfan.androidfundamental.data.response.RestaurantResponse
import com.zharfan.androidfundamental.databinding.ActivityRetrofitBinding
import com.zharfan.androidfundamental.viewmodel.LiveDataApiViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrofitBinding
    private lateinit var reviewAdapter: ReviewAdapter
    private val client = ApiConfig.getApiService()

    private val viewModel:LiveDataApiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        initObserver()
        setRecycleView()
        setAction()
    }

    private fun initObserver() {
        viewModel.restaurant.observe(this){ restaurant ->
            setRestaurantData(restaurant)
        }

        viewModel.listReview.observe(this){consumerReviews ->
            setReviewData(consumerReviews)
        }

        viewModel.isLoading.observe(this){
            showLoading(it)
        }

        viewModel.snackBarText.observe(this){
            it.getContentIfNotHandled()?.let { text ->
                Snackbar.make(
                    window.decorView.rootView,
                    text,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun setRecycleView() {
        reviewAdapter = ReviewAdapter()
        binding.apply {
            with(rvReview){
                val manager = LinearLayoutManager(this@RetrofitActivity)
                layoutManager = manager
                addItemDecoration(DividerItemDecoration(this@RetrofitActivity,manager.orientation))
                adapter = reviewAdapter
            }
        }
    }



    private fun setRestaurantData(restaurant: Restaurant) = with(binding){
        tvTitle.text = restaurant.name
        tvDescription.text = restaurant.description
        Glide.with(this@RetrofitActivity)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(imgPicture)
    }

    private fun setReviewData(consumerReviews: List<CustomerReviewsItem>)= with(binding){
        val listReview =ArrayList<String>()
        for (review in consumerReviews){
            listReview.add(
                """
                    ${review.review}
                    -${review.name}
                """.trimIndent()
            )
        }

        reviewAdapter.setReviews(listReview)
        etReview.setText("")
    }

    private fun setAction() = with(binding){
        btnSend.setOnClickListener {
            viewModel.postReview(etReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken,0)
        }
    }


    private fun showLoading(isShow: Boolean)= with(binding){
        progressBar.visibility = if (isShow){
            View.VISIBLE
        }else{
            View.GONE
        }
    }


}