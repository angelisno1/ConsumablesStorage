package com.inteall.consumablesstorage.api;

import com.inteall.consumablesstorage.entity.HttpResult;
import com.inteall.consumablesstorage.entity.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/8/28.
 */

public interface ApiService {
    @GET("account/login")
    Observable<HttpResult<UserInfo>> login(@Query("userName")String userName, @Query("passWord")String passWord);
}
