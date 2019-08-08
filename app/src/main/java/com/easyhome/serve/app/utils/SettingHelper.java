package com.easyhome.serve.app.utils;

import android.content.Context;
import android.text.TextUtils;

import com.jess.arms.integration.AppManager;
import com.jess.arms.utils.JSONHelper;
import com.easyhome.serve.dao.DaoMaster;
import com.easyhome.serve.dao.DaoSession;
import com.easyhome.serve.dao.KeyValueModelDao;
import com.easyhome.serve.mvp.model.entity.KeyValueModel;

public class SettingHelper {

    private final static String DB_NAME = "JR.db";
    private final static String TRUE = "t";
    private final static String FALSE = "f";

    private static volatile SettingHelper instance;
    private DaoSession daoSession;
    private DaoMaster daoMaster;
    private KeyValueModelDao keyValueModelDao;

    public static SettingHelper getInstance() {
        if (instance == null) {
            synchronized (SettingHelper.class) {
                if (instance == null) {
                    instance = new SettingHelper();

                }
            }
        }
        return instance;
    }

    private SettingHelper() {
        this.daoSession = getDaoSession(AppManager.getAppManager().getApplication());
        this.keyValueModelDao = daoSession.getKeyValueModelDao();
    }

    private DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    private DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public void saveInt(String key, int value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        KeyValueModel keyValueModel = new KeyValueModel();
        keyValueModel.setKey(key);
        keyValueModel.setValue(String.valueOf(value));
        keyValueModelDao.insertOrReplace(keyValueModel);
    }

    public int getInt(String key, int defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        KeyValueModel load = keyValueModelDao.load(key);
        return load == null ? defaultValue : Integer.parseInt(load.getValue());
    }

    public void saveDouble(String key, double value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        KeyValueModel keyValueModel = new KeyValueModel();
        keyValueModel.setKey(key);
        keyValueModel.setValue(String.valueOf(value));
        keyValueModelDao.insertOrReplace(keyValueModel);
    }

    public double getDouble(String key, double defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        KeyValueModel load = keyValueModelDao.load(key);
        return load == null ? defaultValue : Double.parseDouble(load.getValue());
    }

    public void saveBoolean(String key, boolean value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        KeyValueModel keyValueModel = new KeyValueModel();
        keyValueModel.setKey(key);
        keyValueModel.setValue(value ? TRUE : FALSE);
        keyValueModelDao.insertOrReplace(keyValueModel);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        KeyValueModel load = keyValueModelDao.load(key);
        if (load == null) {
            return defaultValue;
        } else {
            String value = load.getValue();
            return value.equals(TRUE) || ((!value.equals(FALSE)) && defaultValue);
        }
    }

    public String getString(String key, String defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        KeyValueModel load = keyValueModelDao.load(key);
        return load == null ? defaultValue : load.getValue();
    }

    public void saveString(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        KeyValueModel keyValueModel = new KeyValueModel();
        keyValueModel.setKey(key);
        keyValueModel.setValue(value);
        keyValueModelDao.insertOrReplace(keyValueModel);
    }

    public void saveObj(String key, Object value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        KeyValueModel keyValueModel = new KeyValueModel();
        keyValueModel.setKey(key);
        keyValueModel.setValue(JSONHelper.getInstance().toJSONString(value));
        keyValueModelDao.insertOrReplace(keyValueModel);
    }

    public <T> T getObj(String key, Class<T> clazz) {

        if (TextUtils.isEmpty(key)) {
            return null;
        }

        KeyValueModel keyValueModel = keyValueModelDao.load(key);
        if (keyValueModel == null) {
            return null;
        }
        String dataJson = keyValueModel.getValue();
        if (TextUtils.isEmpty(dataJson)) {
            return null;
        }

        try {
            return JSONHelper.getInstance().fromGson(dataJson, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

