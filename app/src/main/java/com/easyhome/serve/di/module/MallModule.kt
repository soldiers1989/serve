package com.easyhome.serve.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.fragment.MallContract
import com.easyhome.serve.mvp.model.fragment.MallModel


@Module
//构建MallModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MallModule(private val view: MallContract.View) {
    @FragmentScope
    @Provides
    fun provideMallView(): MallContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideMallModel(model: MallModel): MallContract.Model {
        return model
    }
}
