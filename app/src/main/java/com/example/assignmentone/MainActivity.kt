package com.example.assignmentone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.assignmentone.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
binding.btnSearch.setOnClickListener{


        val retService =RetrofitInstance.getRetrofitInstance().create(PostAPI::class.java)
        val responseLiveData : LiveData<Response<Post>> = liveData {
            val response: Response<Post> =retService.getPost(binding.etPostNumber.text.toString().toInt())
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val  post =it.body()
          if (post !=null){
              Log.i("API",post.title)
              binding.tvId.text = post.id.toString()
              binding.tvUserId.text = post.userId.toString()
              binding.tvTitle.text = post.title
              binding.tvBody.text = post.body
          }
        })








    }
}}