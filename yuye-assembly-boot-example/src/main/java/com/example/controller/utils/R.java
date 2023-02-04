package com.example.controller.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

@Data
public class R {//这个类是为了保证前后端数据格式一直
    private Boolean flag;
    private Object data;

    private String msg;
    public R(){}

    public R(Boolean flag){
        this.flag=flag;
    }

    public R(Boolean flag,Object data){
        this.flag = flag;
        this.data = data;
    }
    public R(Boolean flag,String msg){
        this.flag = flag;
        this.msg = msg;
    }

    public R(String msg){
        this.flag = false;
        this.msg = msg;
    }
}
