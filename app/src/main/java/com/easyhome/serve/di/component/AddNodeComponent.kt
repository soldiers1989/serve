package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.AddNodeModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.AddNodeActivity

@ActivityScope
@Component(modules = arrayOf(AddNodeModule::class), dependencies = arrayOf(AppComponent::class))
interface AddNodeComponent {
    fun inject(activity: AddNodeActivity)
}
