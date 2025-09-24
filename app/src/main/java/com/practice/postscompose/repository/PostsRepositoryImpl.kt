package com.practice.postscompose.repository

import com.practice.postscompose.api.ApiInterface
import com.practice.postscompose.model.Comment
import com.practice.postscompose.model.Post
import retrofit2.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface PostsRepository {
    suspend fun fetchPosts(): Response<List<Post>>
    suspend fun fetchPostById(postId: Int): Response<Post>
    suspend fun fetchPostComments(postId: Int): Response<List<Comment>>

}
class PostsRepositoryImpl (val retrofit: ApiInterface): PostsRepository{


    override suspend fun fetchPosts(): Response<List<Post>> {
        return withContext(Dispatchers.IO) {
            retrofit.fetchPosts()
        }
    }


    override suspend fun fetchPostById(postId: Int): Response<Post>{
        return withContext(Dispatchers.IO){
            retrofit.fetchPostById(postId)
        }
    }

    override suspend fun fetchPostComments(postId: Int): Response<List<Comment>>{
        return withContext(Dispatchers.IO){
            retrofit.fetchPostComments(postId)
        }
    }

}
