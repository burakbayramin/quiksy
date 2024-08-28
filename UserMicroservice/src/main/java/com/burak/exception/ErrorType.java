package com.burak.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    PASS_NOT_MATCH(1001,"pass not match",HttpStatus.BAD_REQUEST),
    USERNAME_PASSWORD_ERROR(1001,"Kullanıcı adı ya da şifre hatalıdır.",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1005,"Geçersiz token",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1006,"Böyle bir kullanıcı kayıtlı değildir.",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR(3001,"Girilen bilgiler Hatalı, kontrol ederek tekar giriniz.", HttpStatus.BAD_REQUEST),
    ALREADY_EXISTS(1003,"already exists",HttpStatus.BAD_REQUEST);



    private int code;
    private String message;
    private HttpStatus httpStatus;

//    ErrorType(int code, String message, HttpStatus httpStatus){
//        this.code = code;
//        this.httpStatus = httpStatus;
//        this.message = message;
//    }
}
