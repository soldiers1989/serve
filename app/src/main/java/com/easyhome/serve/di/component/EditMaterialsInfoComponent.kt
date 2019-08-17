package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.EditMaterialsInfoModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.EditMaterialsInfoActivity

@ActivityScope
@Component(modules = arrayOf(EditMaterialsInfoModule::class), dependencies = arrayOf(AppComponent::class))
interface EditMaterialsInfoComponent {
    fun inject(activity: EditMaterialsInfoActivity)
}
