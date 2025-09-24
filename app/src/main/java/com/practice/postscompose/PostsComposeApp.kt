package com.practice.postscompose

import android.app.Application
import com.practice.postscompose.di.appModules
import org.koin.android.ext.koin.androidContext
import androidx.activity.ComponentActivity
import org.koin.core.context.GlobalContext.startKoin

class PostsComposeApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PostsComposeApp)
            modules(appModules)
        }
    }
}
