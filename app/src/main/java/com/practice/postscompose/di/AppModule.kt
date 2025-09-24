package com.practice.postscompose.di

import com.practice.postscompose.api.ApiInterface
import com.practice.postscompose.model.PostsViewModel
import com.practice.postscompose.repository.PostsRepository
import com.practice.postscompose.repository.PostsRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule=module{
    single{
        HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single<ApiInterface> {
        get<Retrofit>().create(ApiInterface::class.java)
    }

}

val repositoryModule = module {
    single<PostsRepository> {
        PostsRepositoryImpl(get())
    }
}

val viewModelModule = module {
    viewModel {
        PostsViewModel(get())
    }
}
val appModules = listOf(networkModule, repositoryModule, viewModelModule)
