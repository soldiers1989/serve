package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.setting.ISetContract
import com.easyhome.serve.mvp.model.setting.ISetModel


@Module
//构建ISetModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ISetModule(private val view: ISetContract.View) {
    @ActivityScope
    @Provides
    fun provideISetView(): ISetContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideISetModel(model: ISetModel): ISetContract.Model {
        return model
    }
}
