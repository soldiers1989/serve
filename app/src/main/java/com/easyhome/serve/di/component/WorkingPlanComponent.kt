package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.WorkingPlanModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.WorkingPlanActivity

@ActivityScope
@Component(modules = arrayOf(WorkingPlanModule::class), dependencies = arrayOf(AppComponent::class))
interface WorkingPlanComponent {
    fun inject(activity: WorkingPlanActivity)
}
