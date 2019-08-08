package com.jess.arms.base.delegate;

import android.support.v4.app.Fragment;

/**
 * Created by 宗传
 * Time 2018/9/12  下午1:52
 * <p>
 * FragmentNavigatorAdapter
 **/
public interface FragmentNavigatorAdapter {

    public Fragment onCreateFragment(int position);

    public String getTag(int position);

    public int getCount();
}
