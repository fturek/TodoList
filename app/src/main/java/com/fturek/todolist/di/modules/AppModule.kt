package com.fturek.todolist.di.modules

import com.fturek.todolist.data.remote.ApiService
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    internal fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }


    @Provides
    @Singleton
    internal fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }


    @Provides
    @Singleton
    internal fun providesRx2JavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }


    @Provides
    @Singleton
    internal fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder().addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .baseUrl("Base URL")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    internal fun provideTodoCollectionReference(firebaseFirestore: FirebaseFirestore): CollectionReference {
        return firebaseFirestore.collection("todos")
    }

}