package com.baiyun.gcd.tmall.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServiceResponse<T> {

    private int status;
    private String msg;
    private T data;

    public ServiceResponse() {
    }

    public ServiceResponse(int status) {
        this.status = status;
    }

    public ServiceResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ServiceResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public ServiceResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore //使之不再json序列化结果当中
    public boolean isSuccess() {
        return this.status == 1;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ServiceResponse<T> createBySuccess(){
        return new ServiceResponse<T>(1);
    }

    public static <T> ServiceResponse<T> createBySuccessMessage(String msg){
        return new ServiceResponse<T>(200,msg);
    }

    public static <T> ServiceResponse<T> createBySuccess(T data){
        return new ServiceResponse<T>(1,data);
    }

    public static <T> ServiceResponse<T> createBySuccess(String msg,T data){
        return new ServiceResponse<T>(1,msg,data);
    }

    public static <T> ServiceResponse<T> createByError(){
        return  new ServiceResponse<T>(0);
    }

    public static <T> ServiceResponse<T> createByErrorMessage(String errorMessage){
        return new ServiceResponse<T>(0,errorMessage);
    }

    public static  <T> ServiceResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServiceResponse<T>(errorCode,errorMessage);
    }

}
