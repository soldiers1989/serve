package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.DeliveryAddressModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.user.DeliveryAddressActivity

@ActivityScope
@Component(modules = arrayOf(DeliveryAddressModule::class), dependencies = arrayOf(AppComponent::class))
interface DeliveryAddressComponent {
    fun inject(activity: DeliveryAddressActivity)
}
