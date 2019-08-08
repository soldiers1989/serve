package com.easyhome.serve.app.converter;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import com.easyhome.serve.app.HttpResultException;
import com.easyhome.serve.mvp.model.entity.HttpResult;
import com.easyhome.serve.mvp.model.entity.ErrorInfo;
import okhttp3.ResponseBody;
import retrofit2.Converter;


public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();

        HttpResult httpResult = gson.fromJson(response, HttpResult.class);
        if (httpResult.isSuccess()) {
            return gson.fromJson(response, type);
        } else {
            if (httpResult.isSucce()) { //判断外网IP地址
                return gson.fromJson(response, type);
            } else
                throw new HttpResultException(new ErrorInfo(httpResult.getCode(), httpResult.getMessage()));
        }
    }
}