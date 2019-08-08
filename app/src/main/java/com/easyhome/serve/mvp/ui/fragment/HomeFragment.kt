package com.easyhome.serve.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.holder.Holder
import com.bigkoo.convenientbanner.listener.OnPageChangeListener
import com.jess.arms.base.BaseActivity

import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerHomeComponent
import com.easyhome.serve.di.module.HomeModule
import com.easyhome.serve.mvp.contract.fragment.HomeContract
import com.easyhome.serve.mvp.presenter.fragment.HomePresenter

import com.easyhome.serve.R
import com.easyhome.serve.api.RequestCodeInfo
import com.easyhome.serve.app.base.JRBaseFragment
import com.easyhome.serve.app.extension.loadImage
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.activity.Main2Activity
import com.easyhome.serve.mvp.ui.activity.search.CityPickerActivity
import com.easyhome.serve.mvp.ui.adapter.MedicineAdapter
import com.easyhome.serve.mvp.ui.adapter.ProjectDynamicTabAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * 首页
 */
class HomeFragment : JRBaseFragment<HomePresenter>(), HomeContract.View {
    override fun getMyself(): BaseActivity<*> = this.activity as Main2Activity

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        cityTV.singleClick {
            startActivityForResult(Intent(activity, CityPickerActivity::class.java), RequestCodeInfo.GETCITY)
        }
        searchLL.singleClick {
        }


        var mutableList = mutableListOf<String>(
            "http://d.paper.i4.cn/max/2017/03/23/14/1490249222986_905517.jpg",
            "http://pic34.photophoto.cn/20150202/0005018384491898_b.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2095807035,495047869&fm=26&gp=0.j"
        )
        /* for (vp in it) {
             mutableList.add(vp.a6)
         }*/

        // 轮播图
        (banner as ConvenientBanner<String>)
            .setPages(object : CBViewHolderCreator {
                override fun createHolder(itemView: View) = ImageHolderView(itemView)
                override fun getLayoutId() = R.layout.item_image
            }, mutableList)
            .setOnPageChangeListener(object : OnPageChangeListener {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                }

                override fun onPageSelected(index: Int) {
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                }
            })
            .setPageIndicator(intArrayOf(R.mipmap.banner_1, R.mipmap.banner_2))
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
            .setOnItemClickListener { position ->
                //                        openGallery(mutableList, it)
                //  openH5ForUrl(it[position].a8.strToString())
            }
        banner.startTurning()


        //设置科室
        rvDynamicTab.adapter = ProjectDynamicTabAdapter(
            arrayListOf(
                ProjectDynamicTabAdapter.DynamicTabInfo(R.mipmap.user_icon_1, "设计师"),
                ProjectDynamicTabAdapter.DynamicTabInfo(R.mipmap.user_icon_1, "设计师"),
                ProjectDynamicTabAdapter.DynamicTabInfo(R.mipmap.user_icon_1, "设计师"),
                ProjectDynamicTabAdapter.DynamicTabInfo(R.mipmap.user_icon_1, "设计师"),
                ProjectDynamicTabAdapter.DynamicTabInfo(R.mipmap.user_icon_1, "设计师")
            )
        )
        //设置医院
     //   hospitalRV.adapter


        //设置药品
        medicineRV.adapter = MedicineAdapter(
            arrayListOf(
                MedicineAdapter.Info("http://d.paper.i4.cn/max/2017/03/23/14/1490249222986_905517.jpg", "建材", 1.0f),
                MedicineAdapter.Info("http://d.paper.i4.cn/max/2017/03/23/14/1490249222986_905517.jpg", "建材", 1.0f),
                MedicineAdapter.Info("http://d.paper.i4.cn/max/2017/03/23/14/1490249222986_905517.jpg", "建材", 1.0f),
                MedicineAdapter.Info("http://d.paper.i4.cn/max/2017/03/23/14/1490249222986_905517.jpg", "建材", 1.0f)
            )
        )


        homeIV1.singleClick {
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

    inner class ImageHolderView(itemView: View) : Holder<String>(itemView) {
        private var imageView: ImageView? = null

        override fun initView(itemView: View) {
            imageView = itemView.findViewById(R.id.ivBanner)
        }

        override fun updateUI(url: String) {
            imageView?.loadImage(url)
        }
    }
}
