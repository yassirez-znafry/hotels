package com.example.demo.exceptions;

import org.springframework.mail.MailException;

public class SpringHotelManagerException extends RuntimeException {
    public SpringHotelManagerException(String exMessage, MailException exception) {
        super(exMessage, exception);
    }
    public SpringHotelManagerException(String exMessage) {
        super(exMessage);
    }
}
