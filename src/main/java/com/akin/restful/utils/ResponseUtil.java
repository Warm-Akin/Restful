package com.akin.restful.utils;

import com.akin.restful.common.HttpCode;
import com.akin.restful.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity success(Object object) {
        Result<Object> result = new Result<>(object);
        result.setCode(HttpCode.SUCCESS);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    public static ResponseEntity responseCustomException(String code, String message, Object data) {
        Result<Object> result = new Result<>(code);
        result.setMsg(message);
        result.setData(data);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}
