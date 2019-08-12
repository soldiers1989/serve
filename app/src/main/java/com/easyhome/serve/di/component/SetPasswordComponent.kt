package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.SetPasswordModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.SetPasswordActivity

@ActivityScope
@Component(modules = arrayOf(SetPasswordModule::class), dependencies = arrayOf(AppComponent::class))
interface SetPasswordComponent {
    fun inject(activity: SetPasswordActivity)
}
