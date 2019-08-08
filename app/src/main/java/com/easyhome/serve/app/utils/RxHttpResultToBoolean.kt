package com.easyhome.serve.app.utils

import com.easyhome.serve.mvp.model.entity.HttpResult
import io.reactivex.functions.Function


class RxHttpResultToBoolean<T> : Function<HttpResult<T>, Boolean> {
    override fun apply(t: HttpResult<T>): Boolean {
        return true
    }
}