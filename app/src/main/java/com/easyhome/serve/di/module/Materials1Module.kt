package com.easyhome.serve.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.fragment.Materials1Contract
import com.easyhome.serve.mvp.model.fragment.Materials1Model


@Module
//构建Materials1Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class Materials1Module(private val view: Materials1Contract.View) {
    @FragmentScope
    @Provides
    fun provideMaterials1View(): Materials1Contract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideMaterials1Model(model: Materials1Model): Materials1Contract.Model {
        return model
    }
}
