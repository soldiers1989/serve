package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.AddDynamicModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.AddDynamicActivity

@ActivityScope
@Component(modules = arrayOf(AddDynamicModule::class), dependencies = arrayOf(AppComponent::class))
interface AddDynamicComponent {
    fun inject(activity: AddDynamicActivity)
}
