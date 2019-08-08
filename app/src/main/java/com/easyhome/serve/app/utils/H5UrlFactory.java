package com.easyhome.serve.app.utils;


import com.easyhome.serve.api.Api;
import com.easyhome.serve.app.manager.UserInfoManager;

public class H5UrlFactory {

    private static volatile H5UrlFactory instance;

    public static H5UrlFactory getInstance() {
        if (instance == null) {
            synchronized (H5UrlFactory.class) {
                if (instance == null) {
                    instance = new H5UrlFactory();

                }
            }
        }
        return instance;
    }

    private H5UrlFactory() {
    }

    public String getUrlForAddress(String address) {
        return address +
                "&userId=" + UserInfoManager.getInstance().getUserId() +
                "&companyID=" + UserInfoManager.getInstance().getCompanyID() +
                "&authorization=" + UserInfoManager.getInstance().getUserToken();
    }

    private String getBaseCommon(String type, String projectNo) {
        return Api.APP_BASE_URL + "approvalapi/res/deal?resCode=" + type + "&projectNo=" + projectNo + "&authorization=" + UserInfoManager.getInstance().getUserToken();
    }

    /**
     * 设计图纸
     */
    public String getUrlForSJTZ(String projectNo) {
        return getBaseCommon("SJTZ", projectNo);
    }

    /**
     * 材料订单
     */
    public String getUrlForCLDD(String projectNo) {
        return getBaseCommon("CLDD", projectNo);
    }

    /**
     * 开工报告
     */
    public String getUrlForKGBG(String projectNo) {
        return getBaseCommon("KGBG", projectNo);
    }

    /**
     * 施工审批
     */
    public String getUrlForSGSP(String projectNo) {
        return getBaseCommon("SGSP", projectNo);
    }

    /**
     * 竣工资料
     */
    public String getUrlForJGZL(String projectNo) {
        return getBaseCommon("JGZL", projectNo);
    }

    /**
     * 变更资料
     */
    public String getUrlForBGZL(String projectNo) {
        return getBaseCommon("BGZL", projectNo);
    }

    /**
     * 用户隐私
     */
    public String getUrlForUSER_XY() {
        return getBaseCommon("USER_XY", "");
    }

    /**
     * 使用条款
     */
    public String getUrlForUSER_TK() {
        return getBaseCommon("USER_TK", "");
    }

    /**
     * 关于公司
     */
    public String getUrlForABOUT_US() {
        return getBaseCommon("ABOUT_US", "");
    }

    /**
     * 甘特图
     */
    public String getUrlForGANTT_CHART(String projectNo) {
        return getBaseCommon("GANTT_CHART", projectNo);
    }

}
