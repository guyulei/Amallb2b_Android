package com.amall360.amallb2b_android.net;

import com.amall360.amallb2b_android.bean.DomainListBean;
import com.amall360.amallb2b_android.bean.ceshi.UserCheckBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

//11111111111
public interface BBMApiStores {

    String WorkerApiBase = "http://120.26.1.222/api/";

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

    /*@GET("/group/{id}/users")
    List<User> groupList(@Query("sort") String sort);*/

    /*@FormUrlEncoded
    @POST("/user/edit")
    User updateUser(@Field("first_name") String first, @Field("last_name") String last);*/

    //测试  username  mobile
    @FormUrlEncoded
    @POST("userCheck")
    Observable<UserCheckBean> setuserCheck(@Field("key") String first);

    ///api/domainList
    /* *type : 0 ;               -int/string （0-获取省份  1-获取城市）
    provinceid : 11;    -int/string （省份id，type=1时需传参）*/
    @GET("domainList")
    Observable<DomainListBean> getdomainList(@Query("key") String key);


}
