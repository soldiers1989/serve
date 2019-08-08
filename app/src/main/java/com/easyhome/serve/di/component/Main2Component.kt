package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.Main2Module

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.MainActivity

@ActivityScope
@Component(modules = arrayOf(Main2Module::class), dependencies = arrayOf(AppComponent::class))
interface Main2Component {
    fun inject(activity: MainActivity)
}
