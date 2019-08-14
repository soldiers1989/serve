package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.AbarbeitungInfoModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.projet.AbarbeitungInfoActivity

@ActivityScope
@Component(modules = arrayOf(AbarbeitungInfoModule::class), dependencies = arrayOf(AppComponent::class))
interface AbarbeitungInfoComponent {
    fun inject(activity: AbarbeitungInfoActivity)
}
