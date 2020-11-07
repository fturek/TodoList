package com.fturek.todolist.di.viewmodels

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}