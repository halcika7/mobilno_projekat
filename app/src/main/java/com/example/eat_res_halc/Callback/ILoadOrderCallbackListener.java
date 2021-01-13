package com.example.eat_res_halc.Callback;

import com.example.eat_res_halc.Model.Order;

import java.util.List;

public interface ILoadOrderCallbackListener {
    void onLoadOrderSuccess(List<Order> orderList);

    void onLoadOrderFailed(String message);
}
