package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.NewAbarbeitungModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.NewAbarbeitungActivity

@ActivityScope
@Component(modules = arrayOf(NewAbarbeitungModule::class), dependencies = arrayOf(AppComponent::class))
interface NewAbarbeitungComponent {
    fun inject(activity: NewAbarbeitungActivity)
}
