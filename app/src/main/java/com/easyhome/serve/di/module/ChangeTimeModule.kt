package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.ChangeTimeContract
import com.easyhome.serve.mvp.model.project.ChangeTimeModel


@Module
//构建ChangeTimeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ChangeTimeModule(private val view: ChangeTimeContract.View) {
    @ActivityScope
    @Provides
    fun provideChangeTimeView(): ChangeTimeContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideChangeTimeModel(model: ChangeTimeModel): ChangeTimeContract.Model {
        return model
    }
}
