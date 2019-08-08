package com.easyhome.serve.mvp.contract.fragment

import com.jess.arms.mvp.IModel
import com.easyhome.serve.app.base.IView


interface MessageContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel

}
