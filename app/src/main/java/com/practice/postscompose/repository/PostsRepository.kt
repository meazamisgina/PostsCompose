package com.practice.postscompose.repository

import com.practice.postscompose.api.ApiClient
import com.practice.postscompose.api.ApiInterface
import com.practice.postscompose.model.Comment
import com.practice.postscompose.model.Post
import retrofit2.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsRepository {
    val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun fetchPosts(): Response<List<Post>> {
        return withContext(Dispatchers.IO) {
            retrofit.fetchPosts()
        }
    }


    suspend fun fetchPostById(postId: Int): Response<Post>{
        return withContext(Dispatchers.IO){
            retrofit.fetchPostById(postId)
        }
    }

    suspend fun fetchPostComments(postId: Int): Response<List<Comment>>{
        return withContext(Dispatchers.IO){
            retrofit.fetchPostComments(postId)
        }
    }

}
