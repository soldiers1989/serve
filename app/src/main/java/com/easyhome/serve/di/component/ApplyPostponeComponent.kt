package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.ApplyPostponeModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.ApplyPostponeActivity

@ActivityScope
@Component(modules = arrayOf(ApplyPostponeModule::class), dependencies = arrayOf(AppComponent::class))
interface ApplyPostponeComponent {
    fun inject(activity: ApplyPostponeActivity)
}
