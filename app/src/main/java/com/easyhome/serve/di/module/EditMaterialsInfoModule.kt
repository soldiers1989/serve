package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.EditMaterialsInfoContract
import com.easyhome.serve.mvp.model.project.EditMaterialsInfoModel


@Module
//构建EditMaterialsInfoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class EditMaterialsInfoModule(private val view: EditMaterialsInfoContract.View) {
    @ActivityScope
    @Provides
    fun provideEditMaterialsInfoView(): EditMaterialsInfoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideEditMaterialsInfoModel(model: EditMaterialsInfoModel): EditMaterialsInfoContract.Model {
        return model
    }
}
