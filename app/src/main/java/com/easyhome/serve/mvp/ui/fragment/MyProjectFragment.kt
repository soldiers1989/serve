package com.easyhome.serve.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerMyProjectComponent
import com.easyhome.serve.di.module.MyProjectModule
import com.easyhome.serve.mvp.contract.fragment.MyProjectContract
import com.easyhome.serve.mvp.presenter.fragment.MyProjectPresenter

import com.easyhome.serve.R
import com.easyhome.serve.mvp.ui.activity.project.ProjectActivity
import com.easyhome.serve.mvp.ui.adapter.MyProjectAdapter
import kotlinx.android.synthetic.main.fragment_my_project.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * 我的项目
 */
class MyProjectFragment : BaseFragment<MyProjectPresenter>(), MyProjectContract.View {
    companion object {
        fun newInstance(): MyProjectFragment {
            val fragment = MyProjectFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMyProjectComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myProjectModule(MyProjectModule(this))
            .build()
            .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_my_project, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        tvPageTitle.text = "我的项目"
        val adapter = MyProjectAdapter(arrayListOf("", "", "", ""))
        adapter.setOnItemClickListener { adapter, view, position ->
            startActivity<ProjectActivity>()

        }
        projetRV.adapter = adapter
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

    override fun showLoading() {

    }

    override fun hideLoading() {

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
