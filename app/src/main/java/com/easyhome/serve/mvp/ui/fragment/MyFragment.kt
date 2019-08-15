package com.easyhome.serve.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jess.arms.base.BaseActivity

import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerMyComponent
import com.easyhome.serve.di.module.MyModule
import com.easyhome.serve.mvp.contract.fragment.MyContract
import com.easyhome.serve.mvp.presenter.fragment.MyPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseFragment
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.activity.MainActivity
import com.easyhome.serve.mvp.ui.activity.setting.AboutAppActivity
import com.easyhome.serve.mvp.ui.activity.setting.ISetActivity
import com.easyhome.serve.mvp.ui.activity.user.UserDataActivity
import com.easyhome.serve.mvp.ui.adapter.MySelectAdapter
import kotlinx.android.synthetic.main.fragment_my.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * 我的
 */
class MyFragment : JRBaseFragment<MyPresenter>(), MyContract.View {
    override fun getMyself(): BaseActivity<*> = this.activity as MainActivity

    companion object {
        fun newInstance(): MyFragment {
            val fragment = MyFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMyComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myModule(MyModule(this))
            .build()
            .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        val adapter = MySelectAdapter(
            arrayListOf(
                MySelectAdapter.Info(R.mipmap.my_icon_2, "关于APP"),
                MySelectAdapter.Info(R.mipmap.my_icon_1, "清理缓存"),
                MySelectAdapter.Info(R.mipmap.my_icon_3, "设置")
            )
        )
        adapter.setOnItemClickListener { adapter, view, position ->

            when (position) {
                0 -> {
                    startActivity<AboutAppActivity>()
                }
                1 -> {

                }
                2 -> {
                   startActivity<ISetActivity>()
                }

            }

        }
        setItem.adapter = adapter
        userinfoTV.singleClick {
            startActivity<UserDataActivity>()
        }
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     *fun setData(data:Any) {
     *   if(data is Message){
     *       when (data.what) {
     *           0-> {
     *               //根据what 做你想做的事情
     *           }
     *           else -> {
     *           }
     *       }
     *   }
     *}
     *
     *
     *
     *
     *
     * // call setData(Object):
     * val data = Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    override fun setData(data: Any?) {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {

    }
}
