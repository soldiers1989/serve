package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.EditUserDataModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.EditUserDataActivity

@ActivityScope
@Component(modules = arrayOf(EditUserDataModule::class), dependencies = arrayOf(AppComponent::class))
interface EditUserDataComponent {
    fun inject(activity: EditUserDataActivity)
}
