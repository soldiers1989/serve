package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.VerifyModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.VerifyActivity

@ActivityScope
@Component(modules = arrayOf(VerifyModule::class), dependencies = arrayOf(AppComponent::class))
interface VerifyComponent {
    fun inject(activity: VerifyActivity)
}
