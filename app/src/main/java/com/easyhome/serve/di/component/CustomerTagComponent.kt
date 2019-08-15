package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.CustomerTagModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.CustomerTagActivity

@ActivityScope
@Component(modules = arrayOf(CustomerTagModule::class), dependencies = arrayOf(AppComponent::class))
interface CustomerTagComponent {
    fun inject(activity: CustomerTagActivity)
}
