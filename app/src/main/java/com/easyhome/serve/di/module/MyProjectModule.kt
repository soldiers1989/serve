package com.easyhome.serve.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.fragment.MyProjectContract
import com.easyhome.serve.mvp.model.fragment.MyProjectModel


@Module
//构建MyProjectModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MyProjectModule(private val view: MyProjectContract.View) {
    @FragmentScope
    @Provides
    fun provideMyProjectView(): MyProjectContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideMyProjectModel(model: MyProjectModel): MyProjectContract.Model {
        return model
    }
}
