package com.practice.postscompose.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.postscompose.repository.PostsRepository
import com.practice.postscompose.repository.PostsRepositoryImpl
import kotlinx.coroutines.launch

class PostsViewModel(val postsRepository: PostsRepository): ViewModel(){
    val posts = MutableLiveData<List<Post>>()
    val uiState = MutableLiveData(UiState())
    val post = MutableLiveData<Post>()
    val comments = MutableLiveData<List<Comment>>()



    fun fetchPosts(){
        viewModelScope.launch {
            uiState.value = uiState.value?.copy(isLoading = true)
            val response =postsRepository.fetchPosts()
            if(response.isSuccessful){
                uiState.value= uiState.value?.copy(isLoading = false,
                success="Posts fetched successfully")

                posts.value = response.body()
            }
            else{
                uiState.value=uiState.value?.copy(isLoading = false,
                    error = response.errorBody()?.string())
            }
        }
    }


    fun fetchPostById(postId: Int){
        viewModelScope.launch {
            uiState.postValue(uiState.value?.copy(isLoading = true))
            val response = postsRepository.fetchPostById(postId)
            if(response.isSuccessful){
                uiState.value= uiState.value?.copy(isLoading = false,
                    success="Posts fetched successfully")

                post.postValue(response.body())
            }
            else{
                uiState.value=uiState.value?.copy(isLoading = false,
                    error = response.errorBody()?.string())

            }
        }
    }


    fun fetchComments(postId:Int){
        viewModelScope.launch {
            uiState.value=uiState.value?.copy(isLoading = true)
            val response=postsRepository.fetchPostComments(postId)
            if(response.isSuccessful){
                comments.value=response.body()
                uiState.value=uiState.value?.copy(isLoading = false,
                    success = "Fetched comments successfully")
            }
            else{
                uiState.value=uiState.value?.copy(isLoading = false,
                    error = response.errorBody()?.string())
            }
        }
    }

}