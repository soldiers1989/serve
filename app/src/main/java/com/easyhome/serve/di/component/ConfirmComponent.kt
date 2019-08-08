package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.ConfirmModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.ConfirmActivity

@ActivityScope
@Component(modules = arrayOf(ConfirmModule::class), dependencies = arrayOf(AppComponent::class))
interface ConfirmComponent {
    fun inject(activity: ConfirmActivity)
}
