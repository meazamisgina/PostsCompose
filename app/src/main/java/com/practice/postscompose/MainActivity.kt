package com.practice.postscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.practice.postscompose.screens.AppNavigation
import com.practice.postscompose.ui.theme.PostsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostsComposeTheme {
                Surface (modifier = Modifier.fillMaxSize().safeContentPadding()){
                    AppNavigation()
                }
            }
        }
    }
}
