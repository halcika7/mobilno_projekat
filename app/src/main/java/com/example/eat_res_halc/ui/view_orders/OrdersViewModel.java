package com.example.eat_res_halc.ui.view_orders;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eat_res_halc.Model.Order;

import java.util.List;

public class OrdersViewModel extends ViewModel {
    private final MutableLiveData<List<Order>> mutableLiveDataOrderList;

    public OrdersViewModel() {
        mutableLiveDataOrderList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Order>> getMutableLiveDataOrderList() {
        return mutableLiveDataOrderList;
    }

    public void setMutableLiveDataOrderList(List<Order> orderList) {
        mutableLiveDataOrderList.setValue(orderList);
    }
}
