package com.example.eat_res_halc.Callback;

import com.example.eat_res_halc.Model.PopularCategory;

import java.util.List;

public interface PopularCallbackListener {
    void onPopularLoadSuccess(List<PopularCategory> popularCategories);

    void onPopularLoadFailed(String message);
}
