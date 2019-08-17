package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.MaterialsListContract
import com.easyhome.serve.mvp.model.project.MaterialsListModel


@Module
//构建MaterialsListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MaterialsListModule(private val view: MaterialsListContract.View) {
    @ActivityScope
    @Provides
    fun provideMaterialsListView(): MaterialsListContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMaterialsListModel(model: MaterialsListModel): MaterialsListContract.Model {
        return model
    }
}
