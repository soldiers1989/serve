package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.FindPasswordModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.FindPasswordActivity

@ActivityScope
@Component(modules = arrayOf(FindPasswordModule::class), dependencies = arrayOf(AppComponent::class))
interface FindPasswordComponent {
    fun inject(activity: FindPasswordActivity)
}
