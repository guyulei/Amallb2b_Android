package com.amall360.amallb2b_android.bean;

/**
 * Created by A on 2017/11/28.
 */

public class LoginBean {

    /**
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJpYXQiOjE1MTE0MjU2OTIsImV4cCI6MTUxMTQyOTI5MiwibmJmIjoxNTExNDI1NjkyLCJqdGkiOiJtdVgwbXcxbWw3dEJXb3J2In0._Z4v0k4BVs1vD-Fe7THSCBSHExXPeCsOF-Xchh2LQDs
     * message : Login Success
     * status_code : 200
     */

    private String token;
    private String message;
    private int    status_code;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
}
