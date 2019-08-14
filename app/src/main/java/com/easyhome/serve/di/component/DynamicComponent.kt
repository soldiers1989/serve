package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.DynamicModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.DynamicActivity

@ActivityScope
@Component(modules = arrayOf(DynamicModule::class), dependencies = arrayOf(AppComponent::class))
interface DynamicComponent {
    fun inject(activity: DynamicActivity)
}
