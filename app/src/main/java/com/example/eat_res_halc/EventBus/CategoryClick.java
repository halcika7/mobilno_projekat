package com.example.eat_res_halc.EventBus;

import com.example.eat_res_halc.Model.CategoryModel;

public class CategoryClick {
    private boolean success;
    private CategoryModel model;

    public CategoryClick(boolean success, CategoryModel model) {
        this.success = success;
        this.model = model;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CategoryModel getModel() {
        return model;
    }

    public void setModel(CategoryModel model) {
        this.model = model;
    }
}
