package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.notification.NotificationInfoContract
import com.easyhome.serve.mvp.model.notification.NotificationInfoModel


@Module
//构建NotificationInfoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class NotificationInfoModule(private val view: NotificationInfoContract.View) {
    @ActivityScope
    @Provides
    fun provideNotificationInfoView(): NotificationInfoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideNotificationInfoModel(model: NotificationInfoModel): NotificationInfoContract.Model {
        return model
    }
}
