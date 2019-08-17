package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.MaterialsOrderInfoContract
import com.easyhome.serve.mvp.model.project.MaterialsOrderInfoModel


@Module
//构建MaterialsOrderInfoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MaterialsOrderInfoModule(private val view: MaterialsOrderInfoContract.View) {
    @ActivityScope
    @Provides
    fun provideMaterialsOrderInfoView(): MaterialsOrderInfoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMaterialsOrderInfoModel(model: MaterialsOrderInfoModel): MaterialsOrderInfoContract.Model {
        return model
    }
}
