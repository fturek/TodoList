package com.fturek.todolist.di.modules

import com.fturek.todolist.di.components.ListTodoSubComponent
import dagger.Module

@Module(
    subcomponents = [
        ListTodoSubComponent::class
    ]
)
class SubcomponentsModule