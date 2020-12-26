package com.example.eat_res_halc.Remote;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CloudFunctions {
    @GET("")
    Observable<ResponseBody> getCustomToken(@Query("access_token") String access_token);
}
