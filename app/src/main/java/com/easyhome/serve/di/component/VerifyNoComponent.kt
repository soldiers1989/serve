package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.VerifyNoModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.VerifyNoActivity

@ActivityScope
@Component(modules = arrayOf(VerifyNoModule::class), dependencies = arrayOf(AppComponent::class))
interface VerifyNoComponent {
    fun inject(activity: VerifyNoActivity)
}
