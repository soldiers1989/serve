package com.easyhome.serve.app.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;


import com.bigkoo.pickerview.OptionsPickerView;
import com.easyhome.serve.R;

import java.util.List;
import java.util.Random;

/**
 * 通用工具类
 */
public class CommonUtils {
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int randomNumber() {
        int max = 100;
        int min = 0;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * 判断是否字段的值是否为空或null或"null"字符串
     *
     * @param str
     * @return
     */
    public static String cleanString(String str) {
        if (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str)) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * convert sp to its equivalent px
     * <p>
     * 将sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * px转dp
     */
    public static float px2dp(Context context, int px) {
        return (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static int compareVersion(String version1, String version2) {
        int temp1 = 0, temp2 = 0;
        int len1 = version1.length(), len2 = version2.length();
        int i = 0, j = 0;
        while (i < len1 || j < len2) {
            temp1 = 0;
            temp2 = 0;
            while (i < len1 && version1.charAt(i) != '.') {
                temp1 = temp1 * 10 + version1.charAt(i++) - '0';
            }
            while (j < len2 && version2.charAt(j) != '.') {
                temp2 = temp2 * 10 + version2.charAt(j++) - '0';

            }
            if (temp1 > temp2) {
                return 1;
            } else if (temp1 < temp2) {
                return -1;
            } else {
                i++;
                j++;

            }

        }
        return 0;
    }

    public static void showPickerView(Context context, List<String> ls, OptionsPickerView.OnOptionsSelectListener listener) {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, listener)

                .setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setBgColor(Color.parseColor("#F6F7F6"))
                .setTitleSize(16)
                .setSubmitColor(context.getResources().getColor(R.color.color50A))
                .setCancelColor(context.getResources().getColor(R.color.color50A))
                .build();

        pvOptions.setPicker(ls);//二级选择器
        pvOptions.show();
    }


}
