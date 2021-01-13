package com.example.eat_res_halc.Remote;

import com.example.eat_res_halc.Model.BraintreeToken;
import com.example.eat_res_halc.Model.BraintreeTransaction;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ICloudFunctions {
    @GET("token")
    Observable<BraintreeToken> getToken(@HeaderMap Map<String, String> headers);

    @POST("checkout")
    @FormUrlEncoded
    Observable<BraintreeTransaction> submitPayment(
            @HeaderMap Map<String, String> headers,
            @Field("amount") double amount,
            @Field("payment_method_nonce") String nonce);
}
