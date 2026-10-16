package org.user_api.exception;

public class ExceptionPage extends RuntimeException {
    public ExceptionPage(String message) {
        super(message);
        getMessage(message) ;
    }

    public String getMessage(String message){
        return message ;
    }
}
