package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.EditCommodityInfoModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.EditCommodityInfoActivity

@ActivityScope
@Component(modules = arrayOf(EditCommodityInfoModule::class), dependencies = arrayOf(AppComponent::class))
interface EditCommodityInfoComponent {
    fun inject(activity: EditCommodityInfoActivity)
}
