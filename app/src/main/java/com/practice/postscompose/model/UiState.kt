package com.practice.postscompose.model

data class UiState(
    val isLoading:Boolean=false,
    val success:String? =null,
    val error:String?=null
)
