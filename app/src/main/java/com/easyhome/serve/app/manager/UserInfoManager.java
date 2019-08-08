package com.easyhome.serve.app.manager;

import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.easyhome.serve.api.Api;
import com.easyhome.serve.app.utils.SettingHelper;
import com.easyhome.serve.mvp.model.entity.LoginInfo;

/**
 * 用户信息管理类
 **/
public class UserInfoManager {

    private static final String KEY_USER_INFO = "KEY_USER_INFO_CONSUMER";

    private static volatile UserInfoManager instance;

    private UserInfoManager() {
    }

    public static UserInfoManager getInstance() {
        if (instance == null) {
            synchronized (UserInfoManager.class) {
                if (instance == null) {
                    instance = new UserInfoManager();
                }
            }
        }
        return instance;
    }

    /**
     * 获取设备唯一标识
     */
    public String getUUID() {
        Cache<String, Object> cache = ArmsUtils.obtainAppComponentFromContext(AppManager.getAppManager().getApplication()).extras();
        Object uuidOfCache = cache.get(Api.KEY_UUID);
        if (uuidOfCache != null) {
            return uuidOfCache.toString();
        }

        String uuidOfData = SettingHelper.getInstance().getString(Api.KEY_UUID, null);
        if (uuidOfData != null) {
            cache.put(Api.KEY_UUID, uuidOfData);
            return uuidOfData;
        }

        String uuid = DeviceUtils.getUniquePsuedoID(AppManager.getAppManager().getApplication());
        cache.put(Api.KEY_UUID, uuid);
        SettingHelper.getInstance().saveString(Api.KEY_UUID, uuid);
        return uuid;
    }

    /**
     * 保存用户信息
     */
    public void saveUserInfo(LoginInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        ArmsUtils.obtainAppComponentFromContext(AppManager.getAppManager().getApplication()).extras().put(KEY_USER_INFO, userInfo);
        SettingHelper.getInstance().saveObj(KEY_USER_INFO, userInfo);
    }

    /**
     * 清理用户数据
     */
    public void cleanUserInfo() {
        Cache<String, Object> cache = ArmsUtils.obtainAppComponentFromContext(AppManager.getAppManager().getApplication()).extras();
        if (cache.get(KEY_USER_INFO) != null) {
            cache.remove(KEY_USER_INFO);
        }
        SettingHelper.getInstance().saveObj(KEY_USER_INFO, null);
    }

    /**
     * 获取用户信息
     */
    public LoginInfo getUserInfo() {
        Cache<String, Object> cache = ArmsUtils.obtainAppComponentFromContext(AppManager.getAppManager().getApplication()).extras();
        Object userInfoCache = cache.get(KEY_USER_INFO);
        if (userInfoCache != null && userInfoCache instanceof LoginInfo) {
            return (LoginInfo) userInfoCache;
        }

        LoginInfo userInfo = SettingHelper.getInstance().getObj(KEY_USER_INFO, LoginInfo.class);
        if (userInfo != null) {
            cache.put(KEY_USER_INFO, userInfo);
            return userInfo;
        }

        return null;
    }

    /**
     * 检验用户是否登录
     *
     * @return
     */
    public boolean checkLogin() {
        return getUserInfo() != null && getUserToken() != null;
    }

    /**
     * 获取用户Token
     */
    public String getUserToken() {
        if (getUserInfo() != null) {
            return getUserInfo().getToken();
        } else {
            return "";
        }
    }

    /**
     * 获取用户ID
     */
    public String getUserId() {
        if (getUserInfo() != null) {
            return getUserInfo().getConsumerId();
        } else {
            return "";
        }
    }

    /**
     * 用户头像
     */
    public String getHeadPortraits() {
        if (getUserInfo() != null) {
            return getUserInfo().getHeadPortraits();
        } else {
            return "";
        }
    }


    public String getConsumerId() {
        if (getUserInfo() != null) {
            return getUserInfo().getConsumerId();
        } else {
            return "";
        }
    }

    /**
     * 获取用户手机号
     */
    public String getUserPhone() {
        if (getUserInfo() != null) {
            return getUserInfo().getPhone();
        } else {
            return "";
        }
    }

    /**
     * 获得权限信息
     */
    public String getRoleId() {
        if (getUserInfo() != null && getUserInfo().getRoleId() != null) {
            return getUserInfo().getRoleId();
        } else {
            return "CC";
        }
    }

    /**
     * 公司ID
     */
    public String getCompanyID() {
        if (getUserInfo() != null) {
            return getUserInfo().getCompanyID();
        } else {
            return "";
        }
    }

    /**
     * 公司名字
     */
    public String getCompanyName() {
        if (getUserInfo() != null) {
            return getUserInfo().getCompanyName();
        } else {
            return "";
        }
    }

    /**
     * 业主名称
     */
    public String getNickName() {
        if (getUserInfo() != null) {
            return getUserInfo().getNickName();
        } else {
            return "";
        }
    }

    /**
     * 是否为工长
     */
    public boolean isCM() {
        return getUserInfo() != null && getRoleId().equals("CM");
    }

    /**
     * 是否为项目经理
     */
    public boolean isCP() {
        return getUserInfo() != null && getRoleId().equals("CP");
    }

    /**
     * 是否为消费者
     */
    public boolean isCC() {
        return getUserInfo() != null && getRoleId().equals("CC");
    }

    /**
     * 是否为管家
     */
    public boolean isCS() {
        return getUserInfo() != null && getRoleId().equals("CS");
    }
}
