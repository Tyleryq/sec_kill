package com.seckill.model.responsedata;

public class ResponseData<T> {
    private int code;   //200 成功,201 失败
    private String msg;
    private T data; //返回数据

    public ResponseData(T data,int code,String msg){
        this.code=code;
        this.data=data;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
