package com.easyhome.serve.mvp.model.entity

import java.io.Serializable

data class MPair<A, B>(
    var first: A,
    var second: B
) : Serializable