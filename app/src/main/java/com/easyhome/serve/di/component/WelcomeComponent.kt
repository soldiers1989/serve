package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.WelcomeModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.WelcomeActivity

@ActivityScope
@Component(modules = arrayOf(WelcomeModule::class), dependencies = arrayOf(AppComponent::class))
interface WelcomeComponent {
    fun inject(activity: WelcomeActivity)
}
