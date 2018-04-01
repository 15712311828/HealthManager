package com.healthmanager.common;

import com.healthmanager.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public JsonResult argumentValidationHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            return JsonResult.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return JsonResult.fail("参数校验错误");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleAllException(Exception e) {
        if(e instanceof BusinessException){

        }
        else{
            log.error("",e);
        }
        return JsonResult.fail(e.getMessage());
    }
}
