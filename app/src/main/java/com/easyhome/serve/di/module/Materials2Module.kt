package com.easyhome.serve.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.fragment.Materials2Contract
import com.easyhome.serve.mvp.model.fragment.Materials2Model


@Module
//构建Materials2Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class Materials2Module(private val view: Materials2Contract.View) {
    @FragmentScope
    @Provides
    fun provideMaterials2View(): Materials2Contract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideMaterials2Model(model: Materials2Model): Materials2Contract.Model {
        return model
    }
}
