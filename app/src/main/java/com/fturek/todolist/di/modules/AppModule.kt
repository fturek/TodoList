package com.fturek.todolist.di.modules

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

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