package com.dd.vo;

import com.dd.enums.ExceptionEnum;

public class Result {
    private int status;
    private String msg;
    private long time;

    public Result(ExceptionEnum e) {
        this.status = e.getCode();
        this.msg = e.getMessage();
        this.time = System.currentTimeMillis();
    }
}
