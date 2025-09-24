package com.practice.postscompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practice.postscompose.model.Post
import com.practice.postscompose.model.PostsViewModel
import com.practice.postscompose.model.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostsScreen(onClickPost: (Int)-> Unit, viewModel: PostsViewModel = koinViewModel()){
    LaunchedEffect(Unit) {
        viewModel.fetchPosts()
    }


    val posts by viewModel.posts.observeAsState()
    val uiState by viewModel.uiState.observeAsState(UiState())


    when{
        uiState.isLoading -> {
            Box(Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }

        }

        uiState.success !=null ->{
            if (posts != null) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(posts!!) { post ->
                        PostCard(post,onClickPost) }
                }
            }


        }

        uiState.error !=null ->{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text=uiState.error.toString()
                )
            }

        }
    }
}

@Composable
fun PostCard(post: Post, onClickPost: (Int) -> Unit){

    Card(
        onClick = {onClickPost(post.id)},
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(contentColor = Color.Blue)
    ) {
        Column (
            modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp)
        ){
            Text(text = post.title, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text(text = post.body)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostCardPreview() {
    val post = Post(
        userId = 1,
        id = 2,
        title = "",
        body = " "
    )
    PostCard(post = post, {})
}
