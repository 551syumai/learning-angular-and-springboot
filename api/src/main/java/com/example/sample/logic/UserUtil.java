package com.example.sample.logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

public class UserUtil {
    
    /**
     * Get formatted current date and time
     * 
     * @return String 
     */
    public static String getNowDate(){
        LocalDateTime nowDateTime = LocalDateTime.now();
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分");
        return dateformat.format(nowDateTime);
    }

    /**
     * Generate validation error message
     * 
     * @param BindingResult
     * 
     * @return String 
     */
    public static String genValidationErrMsg(BindingResult result){
        String errMsg = "";
        for (ObjectError error : result.getAllErrors()) {
            errMsg += error.getDefaultMessage() + "<br>";
        }
        return errMsg;
    }

    /**
     * check request validation and return 400 exception response
     * 
     * @param BindingResult
     * 
     * @return ResponseEntity 
     */
    public static void checkRequestValidation(BindingResult result){
        if (result.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UserUtil.genValidationErrMsg(result));
    }
}