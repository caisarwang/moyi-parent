package com.dd.exception;

import com.dd.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
