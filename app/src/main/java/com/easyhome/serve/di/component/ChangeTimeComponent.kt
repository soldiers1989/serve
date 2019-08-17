package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.ChangeTimeModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.ChangeTimeActivity

@ActivityScope
@Component(modules = arrayOf(ChangeTimeModule::class), dependencies = arrayOf(AppComponent::class))
interface ChangeTimeComponent {
    fun inject(activity: ChangeTimeActivity)
}
