package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.EditUserDataContract
import com.easyhome.serve.mvp.model.project.EditUserDataModel


@Module
//构建EditUserDataModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class EditUserDataModule(private val view: EditUserDataContract.View) {
    @ActivityScope
    @Provides
    fun provideEditUserDataView(): EditUserDataContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideEditUserDataModel(model: EditUserDataModel): EditUserDataContract.Model {
        return model
    }
}
