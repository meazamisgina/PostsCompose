package com.practice.postscompose.api

import com.practice.postscompose.model.Comment
import com.practice.postscompose.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiInterface {
    @GET("/posts")
    suspend fun fetchPosts(): retrofit2.Response<List<Post>>

    @GET("/posts/{postId}")
    suspend fun fetchPostById(@Path("postId") postId: Int): retrofit2.Response<Post>


    @GET("/posts/{postId}/comments")
    suspend fun fetchPostComments(@Path("postId")postId: Int): Response<List<Comment>>
}
