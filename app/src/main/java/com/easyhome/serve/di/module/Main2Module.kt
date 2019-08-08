package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.Main2Contract
import com.easyhome.serve.mvp.model.Main2Model


@Module
//构建Main2Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class Main2Module(private val view: Main2Contract.View) {
    @ActivityScope
    @Provides
    fun provideMain2View(): Main2Contract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMain2Model(model: Main2Model): Main2Contract.Model {
        return model
    }
}
