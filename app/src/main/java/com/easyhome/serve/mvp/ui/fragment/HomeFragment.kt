package com.easyhome.serve.mvp.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.jess.arms.base.BaseActivity

import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerHomeComponent
import com.easyhome.serve.di.module.HomeModule
import com.easyhome.serve.mvp.contract.fragment.HomeContract
import com.easyhome.serve.mvp.presenter.fragment.HomePresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseFragment
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.activity.MainActivity
import com.easyhome.serve.mvp.ui.activity.ScheduleActivity
import com.easyhome.serve.mvp.ui.activity.project.AddTaskActivity
import com.easyhome.serve.mvp.ui.activity.project.MapActivity
import com.easyhome.serve.mvp.ui.adapter.*
import com.haibin.calendarview.Calendar
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.startActivity
import java.util.HashMap


/**
 * 工作台
 */
class HomeFragment : JRBaseFragment<HomePresenter>(), HomeContract.View {


    //年
    var year: Int = 1970
    //月
    var month = 1

    override fun getMyself(): BaseActivity<*> = this.activity as MainActivity

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


        val str1="总数<font color='#FF0000'>10</font>个"
        itemTV2.setText(Html.fromHtml(str1))
        val str2="未完成<font color='#FF0000'>3</font>个"
        itemTV3.setText(Html.fromHtml(str2))


        schedule.singleClick {
            startActivity<ScheduleActivity>()
        }
        add.singleClick {
            startActivity<AddTaskActivity>()
        }

        statisticsRV1.adapter = HomeStatistics1Adapter(arrayListOf("", ""))
        statisticsRV2.adapter = HomeStatistics2Adapter(arrayListOf("", "", "", ""))
        val wAdapter = WaitThingAdapter(arrayListOf("", "", "", "", "", "", "", "", "", ""))
        wAdapter.setOnItemChildClickListener { adapter, view, position ->

            when (view.id) {
                R.id.locationTV -> {
                    startActivity<MapActivity>()
                }
            }
        }
        waitThing.adapter = wAdapter


        year = mCalendarView.getCurYear()
        month = mCalendarView.getCurMonth()

        /*val map = HashMap<String, Calendar>()
        map[getSchemeCalendar(year, month, 3, -0x02EA0D, "").toString()] =
                getSchemeCalendar(year, month, 3, -0x02EA0D, "")
        map[getSchemeCalendar(year, month, 6, -0x196ec8, "").toString()] =
                getSchemeCalendar(year, month, 6, -0x196ec8, "")

        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map)*/
        // mCalendarView.setBackground(Color.parseColor("#ffffff"),Color.parseColor("#3669F8"),Color.parseColor("#ffffff"))

        dateTV.text = "${year}年${month}月"
        topTime.text = "${year}年${month}月"
        up.singleClick {
            mCalendarView.scrollToPre()
            month -= 1
            if (month < 1) {
                year -= 1
                month = 12
            }
            dateTV.text = "${year}年${month}月"
            topTime.text = "${year}年${month}月"
        }

        down.singleClick {
            mCalendarView.scrollToNext()
            month += 1
            if (month >12) {
                year += 1
                month = 1
            }
            dateTV.text = "${year}年${month}月"
            topTime.text = "${year}年${month}月"
        }


        homeSV.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {

                //监听滚动状态
                //判断某个控件是否可见
                val scrollBounds = Rect()
                homeSV.getHitRect(scrollBounds)
                if (scrollY > oldScrollY) {//上划
                    // println("上划-------")

                    if (!bacV.getLocalVisibleRect(scrollBounds) && topCalendar.visibility != View.VISIBLE) {//不可见
                        val animation = AnimationUtils.loadAnimation(activity, R.anim.gradually_show);
                        topCalendar.startAnimation(animation);
                        topCalendar.visibility = View.VISIBLE
                    }


                }
                if (scrollY < oldScrollY) {//下划
                    //    println("下划-------")
                    if (bacV.getLocalVisibleRect(scrollBounds)) {//不可见
                        topCalendar.visibility = View.GONE
                    }

                }

                if (scrollY == 0) {// 滚动到顶
                    // dataRB1.isChecked = true
                }
                // 滚动到底
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                }


            }
        })
    }

    private fun getSchemeCalendar(year: Int, month: Int, day: Int, color: Int, text: String): Calendar {
        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
        calendar.schemeColor = color//如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        calendar.addScheme(Calendar.Scheme())
        calendar.addScheme(-0xff0000, "")
        return calendar
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
