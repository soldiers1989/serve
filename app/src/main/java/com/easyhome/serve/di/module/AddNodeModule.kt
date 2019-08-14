package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.AddNodeContract
import com.easyhome.serve.mvp.model.project.AddNodeModel


@Module
//构建AddNodeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class AddNodeModule(private val view: AddNodeContract.View) {
    @ActivityScope
    @Provides
    fun provideAddNodeView(): AddNodeContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAddNodeModel(model: AddNodeModel): AddNodeContract.Model {
        return model
    }
}
