package com.easyhome.serve.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerMyProjectComponent
import com.easyhome.serve.di.module.MyProjectModule
import com.easyhome.serve.mvp.contract.fragment.MyProjectContract
import com.easyhome.serve.mvp.presenter.fragment.MyProjectPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.model.entity.MPair
import com.easyhome.serve.mvp.ui.activity.project.MapActivity
import com.easyhome.serve.mvp.ui.activity.project.ProjectActivity
import com.easyhome.serve.mvp.ui.adapter.CitySelectorAdapter
import com.easyhome.serve.mvp.ui.adapter.MyProjectAdapter
import com.easyhome.serve.mvp.ui.adapter.SelectItemAdapter
import com.easyhome.serve.util.StringUtil
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
        adapter.setOnItemChildClickListener { adapter, view, position ->

            when (view.id) {
                R.id.nameTV -> {
                    startActivity<MapActivity>()
                }
            }
        }

        val d1 = arrayListOf(
            MPair(true, "全部"),
            MPair(false, "未发起"),
            MPair(false, "已发起"),
            MPair(false, "已确认")
        )
        val adapter1 = SelectItemAdapter(d1)
        adapter1.setOnItemClickListener { adapter, view, position ->
            d1.forEachIndexed { index, mPair ->
                var b = index == position
                mPair.first = b
            }

            adapter1.notifyDataSetChanged()
        }
        selectRV1.adapter = adapter1
        val d2 = arrayListOf(
            MPair(true, "全部"),
            MPair(false, "未发起"),
            MPair(false, "已发起"),
            MPair(false, "已指派"),
            MPair(false, "已确认")
        )
        val adapter2 = SelectItemAdapter(d2)
        adapter2.setOnItemClickListener { adapter, view, position ->
            d2.forEachIndexed { index, mPair ->
                var b = index == position
                mPair.first = b
            }

            adapter2.notifyDataSetChanged()
        }
        selectRV2.adapter = adapter2
        val d3 = arrayListOf(
            MPair(true, "全部"), MPair(false, "未开工"), MPair(false, "已开工"), MPair(false, "隐藏"),
            MPair(false, "中期"), MPair(false, "基础"), MPair(false, "竣工"), MPair(false, "结算")
        )
        val adapter3 = SelectItemAdapter(d3)
        adapter3.setOnItemClickListener { adapter, view, position ->
            d3.forEachIndexed { index, mPair ->
                var b = index == position
                mPair.first = b
            }
            adapter3.notifyDataSetChanged()
        }
        selectRV3.adapter = adapter3
        projetRV.adapter = adapter
        filtrateTV.singleClick {
            locationCL.visibility = View.GONE
            if (labelsLL.visibility == View.VISIBLE) {
                labelsLL.visibility = View.GONE
                impede.visibility = View.GONE
            } else {
                labelsLL.visibility = View.VISIBLE
                impede.visibility = View.VISIBLE
            }
        }
        cityTV.singleClick {
            labelsLL.visibility = View.GONE
            if (locationCL.visibility == View.VISIBLE) {
                locationCL.visibility = View.GONE
                impede.visibility = View.GONE
            } else {
                locationCL.visibility = View.VISIBLE
                impede.visibility = View.VISIBLE

            }
        }

        val data1 = arrayListOf(MPair(true, "北京"), MPair(false, "河北"), MPair(false, "河南"), MPair(false, "天津"))

        val data2 = arrayListOf(
            arrayListOf(MPair(true, "北京")),
            arrayListOf(MPair(true, "石家庄"), MPair(true, "保定"), MPair(true, "承德"), MPair(true, "邯郸")),
            arrayListOf(MPair(true, "郑州"), MPair(true, "开封"), MPair(true, "安阳"), MPair(true, "周口")),
            arrayListOf(MPair(true, "天津"))

        )
        val pAdapter = CitySelectorAdapter(data1)
        val cAdapter = CitySelectorAdapter(data2[1])
        pAdapter.setOnItemClickListener { adapter, view, position ->

            data1.forEachIndexed { index, mPair ->
                mPair.first = index == position
            }

            adapter.notifyDataSetChanged()
            cAdapter.setNewData(data2[position])
        }
        province.adapter = pAdapter
        city.adapter = cAdapter
        reset.singleClick {

        }

        confirm.singleClick {
            labelsLL.visibility = View.GONE
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
