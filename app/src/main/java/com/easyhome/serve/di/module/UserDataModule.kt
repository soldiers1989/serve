package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.user.UserDataContract
import com.easyhome.serve.mvp.model.user.UserDataModel


@Module
//构建UserDataModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class UserDataModule(private val view: UserDataContract.View) {
    @ActivityScope
    @Provides
    fun provideUserDataView(): UserDataContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideUserDataModel(model: UserDataModel): UserDataContract.Model {
        return model
    }
}
