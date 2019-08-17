package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.AssignType2Contract
import com.easyhome.serve.mvp.model.project.AssignType2Model


@Module
//构建AssignType2Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class AssignType2Module(private val view: AssignType2Contract.View) {
    @ActivityScope
    @Provides
    fun provideAssignType2View(): AssignType2Contract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAssignType2Model(model: AssignType2Model): AssignType2Contract.Model {
        return model
    }
}
