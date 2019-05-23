package com.dd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    NO_ID_EXCEPTION(404, "id不能为空");

    private int code;
    private String message;
}
