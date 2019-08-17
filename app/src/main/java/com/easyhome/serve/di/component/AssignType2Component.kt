package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.AssignType2Module

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.AssignType2Activity

@ActivityScope
@Component(modules = arrayOf(AssignType2Module::class), dependencies = arrayOf(AppComponent::class))
interface AssignType2Component {
    fun inject(activity: AssignType2Activity)
}
