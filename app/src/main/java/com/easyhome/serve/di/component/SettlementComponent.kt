package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.SettlementModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.SettlementActivity

@ActivityScope
@Component(modules = arrayOf(SettlementModule::class), dependencies = arrayOf(AppComponent::class))
interface SettlementComponent {
    fun inject(activity: SettlementActivity)
}
