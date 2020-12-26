package com.example.eat_res_halc.Callback;

import com.example.eat_res_halc.Model.BestDeal;

import java.util.List;

public interface BestDealCallbackListener {
    void onBestDealSuccess(List<BestDeal> bestDeals);

    void onBestDealLoadFailed(String message);
}
