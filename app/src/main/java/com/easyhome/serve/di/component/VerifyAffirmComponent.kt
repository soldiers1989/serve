package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.VerifyAffirmModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.VerifyAffirmActivity

@ActivityScope
@Component(modules = arrayOf(VerifyAffirmModule::class), dependencies = arrayOf(AppComponent::class))
interface VerifyAffirmComponent {
    fun inject(activity: VerifyAffirmActivity)
}
