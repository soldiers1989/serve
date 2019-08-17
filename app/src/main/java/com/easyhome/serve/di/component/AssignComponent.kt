package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.AssignModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.AssignActivity

@ActivityScope
@Component(modules = arrayOf(AssignModule::class), dependencies = arrayOf(AppComponent::class))
interface AssignComponent {
    fun inject(activity: AssignActivity)
}
