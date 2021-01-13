package com.example.eat_res_halc.Callback;

import com.example.eat_res_halc.Model.Order;

public interface ILoadTimeFromFirebaseListener {
    void onLoadTimeSuccess(Order order, long estimateTimeInMS);

    void onLoadTimeFailed(String message);
}
