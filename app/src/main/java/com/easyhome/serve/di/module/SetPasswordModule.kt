package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.SetPasswordContract
import com.easyhome.serve.mvp.model.SetPasswordModel


@Module
//构建SetPasswordModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class SetPasswordModule(private val view: SetPasswordContract.View) {
    @ActivityScope
    @Provides
    fun provideSetPasswordView(): SetPasswordContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideSetPasswordModel(model: SetPasswordModel): SetPasswordContract.Model {
        return model
    }
}
