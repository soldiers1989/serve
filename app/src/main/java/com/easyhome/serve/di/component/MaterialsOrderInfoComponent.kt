package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.MaterialsOrderInfoModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.MaterialsOrderInfoActivity

@ActivityScope
@Component(modules = arrayOf(MaterialsOrderInfoModule::class), dependencies = arrayOf(AppComponent::class))
interface MaterialsOrderInfoComponent {
    fun inject(activity: MaterialsOrderInfoActivity)
}
