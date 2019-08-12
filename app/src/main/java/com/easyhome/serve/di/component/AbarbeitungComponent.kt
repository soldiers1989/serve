package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.AbarbeitungModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.AbarbeitungActivity

@ActivityScope
@Component(modules = arrayOf(AbarbeitungModule::class), dependencies = arrayOf(AppComponent::class))
interface AbarbeitungComponent {
    fun inject(activity: AbarbeitungActivity)
}
