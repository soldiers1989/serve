package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.UserDataModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.user.UserDataActivity

@ActivityScope
@Component(modules = arrayOf(UserDataModule::class), dependencies = arrayOf(AppComponent::class))
interface UserDataComponent {
    fun inject(activity: UserDataActivity)
}
