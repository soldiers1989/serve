package com.easyhome.serve.mvp.ui.adapter

import android.support.v4.app.Fragment
import com.jess.arms.base.delegate.FragmentNavigatorAdapter
import com.easyhome.serve.mvp.ui.fragment.HomeFragment
import com.easyhome.serve.mvp.ui.fragment.MessageFragment
import com.easyhome.serve.mvp.ui.fragment.MyFragment
import com.easyhome.serve.mvp.ui.fragment.MyProjectFragment


class HomeFragmentAdapter(val mTabs: Array<String>) : FragmentNavigatorAdapter {


    override fun getTag(index: Int): String = mTabs[index]

    override fun onCreateFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment.newInstance()
            1 -> MyProjectFragment.newInstance()
            2 -> MessageFragment.newInstance()
            else -> MyFragment.newInstance()
        }
    }


    override fun getCount(): Int = mTabs.count()
}