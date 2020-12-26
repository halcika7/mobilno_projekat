package com.example.eat_res_halc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.eat_res_halc.Database.CartDataSource;
import com.example.eat_res_halc.Database.CartDatabase;
import com.example.eat_res_halc.Database.CartItem;
import com.example.eat_res_halc.Database.LocalCartDataSource;
import com.example.eat_res_halc.EventBus.CounterCartEvent;
import com.example.eat_res_halc.EventBus.UpdateItemInCart;
import com.example.eat_res_halc.R;
import com.google.android.material.button.MaterialButton;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {
    Context context;
    List<CartItem> cartItemList;

    public MyCartAdapter(Context context, List<CartItem> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartItem item = cartItemList.get(position);
        Glide.with(context).load(item.getFoodImage()).into(holder.img_cart);
        holder.txt_food_name.setText(new StringBuilder(item.getFoodName()));
        holder.txt_food_price.setText(new StringBuilder().append(item.getFoodPrice() + item.getFoodExtraPrice()));

        holder.numberButton.setNumber(String.valueOf(item.getFoodQuantity()));

        holder.numberButton.setOnValueChangeListener((view, oldValue, newValue) -> {
            //Update database on button click
            item.setFoodQuantity(newValue);
            EventBus.getDefault().postSticky(new UpdateItemInCart(item));
        });

        CartDataSource cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(context).cartDao());

        holder.delete_item_button.setOnClickListener(view -> {
            cartDataSource.deleteCartItem(item)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Integer>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Integer integer) {
                            EventBus.getDefault().postSticky(new CounterCartEvent(true)); //Update FAB button
                            Toast.makeText(context, "Delete item from cart successful", Toast.LENGTH_SHORT).show();
                            EventBus.getDefault().postSticky(new UpdateItemInCart(item));
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public CartItem getItemAtPosition(int pos) {
        return cartItemList.get(pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_cart)
        ImageView img_cart;
        @BindView(R.id.txt_food_price)
        TextView txt_food_price;
        @BindView(R.id.txt_food_name)
        TextView txt_food_name;
        @BindView(R.id.number_button)
        ElegantNumberButton numberButton;
        @BindView(R.id.delete_item_button)
        MaterialButton delete_item_button;

        private Unbinder unbinder;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
