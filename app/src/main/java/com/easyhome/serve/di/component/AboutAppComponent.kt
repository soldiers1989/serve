package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.AboutAppModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.setting.AboutAppActivity

@ActivityScope
@Component(modules = arrayOf(AboutAppModule::class), dependencies = arrayOf(AppComponent::class))
interface AboutAppComponent {
    fun inject(activity: AboutAppActivity)
}
