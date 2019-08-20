package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.AddTaskModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.AddTaskActivity

@ActivityScope
@Component(modules = arrayOf(AddTaskModule::class), dependencies = arrayOf(AppComponent::class))
interface AddTaskComponent {
    fun inject(activity: AddTaskActivity)
}
