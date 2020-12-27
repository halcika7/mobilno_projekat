package com.example.eat_res_halc.EventBus;

import com.example.eat_res_halc.Model.BestDeal;

public class BestDealItemClick {
    private BestDeal bestDeal;

    public BestDealItemClick(BestDeal bestDeal) {
        this.bestDeal = bestDeal;
    }

    public BestDeal getBestDeal() {
        return bestDeal;
    }

    public void setBestDeal(BestDeal bestDeal) {
        this.bestDeal = bestDeal;
    }
}
