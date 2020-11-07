package com.fturek.todolist.di

import dagger.Module

@Module(
    subcomponents = [
        ListTodoSubComponent::class
    ]
)
class SubcomponentsModule {}