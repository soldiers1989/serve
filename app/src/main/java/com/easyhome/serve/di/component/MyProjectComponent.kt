package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.MyProjectModule

import com.jess.arms.di.scope.FragmentScope
import com.easyhome.serve.mvp.ui.fragment.MyProjectFragment

@FragmentScope
@Component(modules = arrayOf(MyProjectModule::class), dependencies = arrayOf(AppComponent::class))
interface MyProjectComponent {
    fun inject(fragment: MyProjectFragment)
}
