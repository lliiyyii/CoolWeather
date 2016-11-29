package com.example.administrator.weather;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
