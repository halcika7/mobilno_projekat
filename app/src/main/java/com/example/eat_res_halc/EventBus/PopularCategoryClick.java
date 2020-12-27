package com.example.eat_res_halc.EventBus;

import com.example.eat_res_halc.Model.PopularCategory;

public class PopularCategoryClick {
    private PopularCategory popularCategory;

    public PopularCategoryClick(PopularCategory popularCategory) {
        this.popularCategory = popularCategory;
    }

    public PopularCategory getPopularCategory() {
        return popularCategory;
    }

    public void setPopularCategory(PopularCategory popularCategory) {
        this.popularCategory = popularCategory;
    }
}
