package com.amall360.amallb2b_android.net;

import com.amall360.amallb2b_android.bean.ceshi.UserCheckBean;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

//11111111111
public interface WorkerApiStores {

    String WorkerApiBase = "http://192.168.0.196:8080/api/userCheck";
    //http://192.168.0.196:8080/api/userCheck?key=2D6EDB9EF6AA6D9F57B1275E851369477AC1870E8D5775D5165753568DB5D27B52B87579D88120D7AC99785895AF026D

    //获取验证码  http://192.168.0.150:8080/amall/app/zxjl/getCodes.html?j_username=15958121433
    /*@GET("zxjl/getCodes.html")
    Observable<List<publicBean>> getCodes(@QueryMap HashMap<String, String> map);*/

    //获取所有的省份 http://hangzhou.amall360.com/app/zxjl/getAllProvince.html
    /*@GET("zxjl/getAllProvince.html")
    Observable<List<AreaBean>> getAllProvince();*/

    //融云推送  http://192.168.0.150:8080/amall/app/destackls/shigongStatusList.html?userId=30928&type=11&tk=dd902a4cd3e64db58e41084ea851daae 当前登录的用户id  type 类型（11为施工动态 12为互动消息）
   /* @GET("destackls/shigongStatusList.html?")
    Observable<List<shigongStatusBean>> shigongStatusList(@QueryMap Map<String, String> map);*/
    // http://192.168.0.150:8080/amall/app/zxjl/callbackPuthToken.html?token=1a0018970a965d6b69e&userId=30928&flag=1&userType
    //极光
    /*@GET("zxjl/callbackPuthToken.html")
    Observable<List<publicBean>> callbackPuthToken(@QueryMap Map<String, String> map);*/

    //工程派工
    /*@FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8;")
    @POST("destackls/addProject.html")
    Observable<List<PublicTwoBean>> setOrderToGcs(@FieldMap HashMap<String, String> map);*/
    //测试  username  mobile
    @FormUrlEncoded
    @POST("userCheck")
    Observable<UserCheckBean> setuserCheck(@FieldMap HashMap<String, String> map);


    /*@GET("userCheck")
    Observable<UserCheckBean> setuserCheck(@QueryMap Map<String, String> map);*/

}
