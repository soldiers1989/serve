package com.easyhome.serve.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static List<String> getLabels1() {
        List<String> ls = new ArrayList<String>();
        ls.add("全部");
        ls.add("未发起");
        ls.add("已发起");
        ls.add("已确认");
        return ls;
    }

    public static List<String> getLabels2() {
        List<String> ls = new ArrayList<String>();
        ls.add("全部");
        ls.add("未发起");
        ls.add("已发起");
        ls.add("已指派");
        ls.add("已确认");
        return ls;
    }

    public static List<String> getLabels3() {
        List<String> ls = new ArrayList<String>();
        ls.add("全部");
        ls.add("未开工");
        ls.add("已开工");
        ls.add("隐藏");
        ls.add("中期");
        ls.add("基础");
        ls.add("竣工");
        ls.add("结算");
        return ls;
    }
}
