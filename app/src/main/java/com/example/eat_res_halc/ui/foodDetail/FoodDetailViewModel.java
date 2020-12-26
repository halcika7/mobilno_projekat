package com.example.eat_res_halc.ui.foodDetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eat_res_halc.Common.Common;
import com.example.eat_res_halc.Model.CommentModel;
import com.example.eat_res_halc.Model.FoodModel;

public class FoodDetailViewModel extends ViewModel {
    private MutableLiveData<FoodModel> mutableLiveDataFood;
    private MutableLiveData<CommentModel> mutableLiveDataComment;

    public FoodDetailViewModel() {
        mutableLiveDataComment = new MutableLiveData<>();
    }

    public void setCommentModel(CommentModel commentModel) {
        if (mutableLiveDataComment != null)
            mutableLiveDataComment.setValue(commentModel);
    }

    public MutableLiveData<CommentModel> getMutableLiveDataComment() {
        return mutableLiveDataComment;
    }

    public MutableLiveData<FoodModel> getMutableLiveDataFood() {
        if (mutableLiveDataFood == null)
            mutableLiveDataFood = new MutableLiveData<>();
        mutableLiveDataFood.setValue(Common.selectedFood);
        return mutableLiveDataFood;
    }

    public void setFoodModel(FoodModel model) {
        if (mutableLiveDataFood != null)
            mutableLiveDataFood.setValue(model);
    }
}
