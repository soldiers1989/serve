package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.setting.NewPasswordContract
import com.easyhome.serve.mvp.model.setting.NewPasswordModel


@Module
//构建NewPasswordModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class NewPasswordModule(private val view: NewPasswordContract.View) {
    @ActivityScope
    @Provides
    fun provideNewPasswordView(): NewPasswordContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideNewPasswordModel(model: NewPasswordModel): NewPasswordContract.Model {
        return model
    }
}
