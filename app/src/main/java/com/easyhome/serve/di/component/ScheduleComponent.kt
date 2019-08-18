package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.ScheduleModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.ScheduleActivity

@ActivityScope
@Component(modules = arrayOf(ScheduleModule::class), dependencies = arrayOf(AppComponent::class))
interface ScheduleComponent {
    fun inject(activity: ScheduleActivity)
}
