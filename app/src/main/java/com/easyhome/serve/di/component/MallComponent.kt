package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.MallModule

import com.jess.arms.di.scope.FragmentScope
import com.easyhome.serve.mvp.ui.fragment.MallFragment

@FragmentScope
@Component(modules = arrayOf(MallModule::class), dependencies = arrayOf(AppComponent::class))
interface MallComponent {
    fun inject(fragment: MallFragment)
}
