package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.CoupleBackModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.setting.CoupleBackActivity

@ActivityScope
@Component(modules = arrayOf(CoupleBackModule::class), dependencies = arrayOf(AppComponent::class))
interface CoupleBackComponent {
    fun inject(activity: CoupleBackActivity)
}
