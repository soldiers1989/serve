package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.NotificationInfoModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.notification.NotificationInfoActivity

@ActivityScope
@Component(modules = arrayOf(NotificationInfoModule::class), dependencies = arrayOf(AppComponent::class))
interface NotificationInfoComponent {
    fun inject(activity: NotificationInfoActivity)
}
