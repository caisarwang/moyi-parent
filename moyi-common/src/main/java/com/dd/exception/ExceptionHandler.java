package com.dd.exception;

import com.dd.enums.ExceptionEnum;
import com.dd.vo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice//拦截所有的加入controller注解的类的请求
public class ExceptionHandler {

//    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Object> exceptionHand(RuntimeException e) {
//        return ResponseEntity.status(404).body(e.getMessage());
//    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResponseEntity<Result> exceptionHand(CustomException e) {
        ExceptionEnum exceptionEnum = e.getExceptionEnum();
        return ResponseEntity.status(404).body(new Result(exceptionEnum));
    }
}
