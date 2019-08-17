package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.EditCommodityInfoContract
import com.easyhome.serve.mvp.model.project.EditCommodityInfoModel


@Module
//构建EditCommodityInfoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class EditCommodityInfoModule(private val view: EditCommodityInfoContract.View) {
    @ActivityScope
    @Provides
    fun provideEditCommodityInfoView(): EditCommodityInfoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideEditCommodityInfoModel(model: EditCommodityInfoModel): EditCommodityInfoContract.Model {
        return model
    }
}
