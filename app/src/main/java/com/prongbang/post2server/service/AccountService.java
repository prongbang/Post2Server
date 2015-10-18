package com.prongbang.post2server.service;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by prongbang on 10/18/2015.
 */
public interface AccountService {

    @FormUrlEncoded         // รูปแบบการส่งเป็น FormUrlEncoded
    @POST("/index.php")     // จะส่งค่าไปที่ไฟล์ index.php ส่งค่าแบบ POST
    void post(@FieldMap Map<String,Object> parameter,Callback<Object> callback);
//  void post(@FieldMap ส่งค่าไปให้ Server แบบ Map ,Callback< ค่าที่ Response จาก Server > callback);

    @FormUrlEncoded
    @POST("/login.php")
    void login(@Field("username") String username,@Field("password") String password);

}
