package com.example.eat_res_halc.EventBus;

import com.example.eat_res_halc.Model.FoodModel;

public class FoodItemClicked {
    private boolean success;
    private FoodModel foodModel;

    public FoodItemClicked(boolean success, FoodModel foodModel) {
        this.success = success;
        this.foodModel = foodModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public FoodModel getFoodModel() {
        return foodModel;
    }

    public void setFoodModel(FoodModel foodModel) {
        this.foodModel = foodModel;
    }
}
