package br.com.optimate.jwt.config.exception;

public class CustomBusinessException extends  RuntimeException {

    public CustomBusinessException(String message) {
        super(message);
    }

}
