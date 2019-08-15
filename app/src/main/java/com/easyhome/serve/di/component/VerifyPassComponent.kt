package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.VerifyPassModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.VerifyPassActivity

@ActivityScope
@Component(modules = arrayOf(VerifyPassModule::class), dependencies = arrayOf(AppComponent::class))
interface VerifyPassComponent {
    fun inject(activity: VerifyPassActivity)
}
