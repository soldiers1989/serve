package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.ISetModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.setting.ISetActivity

@ActivityScope
@Component(modules = arrayOf(ISetModule::class), dependencies = arrayOf(AppComponent::class))
interface ISetComponent {
    fun inject(activity: ISetActivity)
}
