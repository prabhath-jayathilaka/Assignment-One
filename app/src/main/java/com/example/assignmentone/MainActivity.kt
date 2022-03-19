package com.example.assignmentone

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.assignmentone.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnSearch.setOnClickListener {
            searchPost()
        }

        binding.etPostNumber.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                searchPost()
                return@OnKeyListener true
            }
            false
        })


    }

    @SuppressLint("SetTextI18n")
    private fun searchPost() {

        val retService = RetrofitInstance.getRetrofitInstance().create(PostAPI::class.java)
        val responseLiveData: LiveData<Response<Post>> = liveData {
            val response: Response<Post> =
                retService.getPost(binding.etPostNumber.text.toString().toInt())
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val post = it.body()
            if (post != null) {
                Log.i("API", post.title)
                binding.tvId.text = "Past Id :" + post.id.toString()
                binding.tvUserId.text = "User Id :" + post.userId.toString()
                binding.tvTitle.text = "Title :" + post.title
                binding.tvBody.text = "Body :" + post.body
            }
        })
    }
}