package com.burak.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    PASSWORD_NOT_MATCH(1001,"password not match",HttpStatus.BAD_REQUEST),
    USERNAME_PASSWORD_ERROR(1001,"Kullanıcı adı ya da şifre hatalıdır.",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR(3001,"Girilen bilgiler Hatalı, kontrol ederek tekar giriniz.", HttpStatus.BAD_REQUEST),
    TOKEN_ERROR(1004,"Token üretilemedi, lütfen tekrar giriş yapmayı deneyiniz.", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_ALREADY_EXISTS(1003,"user already exist",HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;

}
