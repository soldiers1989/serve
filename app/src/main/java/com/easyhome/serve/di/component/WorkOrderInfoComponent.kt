package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.WorkOrderInfoModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.WorkOrderInfoActivity

@ActivityScope
@Component(modules = arrayOf(WorkOrderInfoModule::class), dependencies = arrayOf(AppComponent::class))
interface WorkOrderInfoComponent {
    fun inject(activity: WorkOrderInfoActivity)
}
