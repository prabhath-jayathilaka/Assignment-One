package com.example.assignmentone

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostAPI {
    @GET("/posts/{id}")
    suspend fun  getPost(@Path("id") id:Int) : Response<Post>


//    @GET("/posts/1")
//    suspend fun  getPost() : Response<Post>
}