package com.example.eat_res_halc.Callback;

import com.example.eat_res_halc.Model.CategoryModel;

import java.util.List;

public interface ICategoryCallbackListener {
    void onCategorySuccess(List<CategoryModel> categoryModelList);

    void onCategoryLoadFailed(String message);
}
