package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.WorkOrderModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.WorkOrderActivity

@ActivityScope
@Component(modules = arrayOf(WorkOrderModule::class), dependencies = arrayOf(AppComponent::class))
interface WorkOrderComponent {
    fun inject(activity: WorkOrderActivity)
}
