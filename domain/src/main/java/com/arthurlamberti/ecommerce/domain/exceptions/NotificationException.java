package com.arthurlamberti.ecommerce.domain.exceptions;


import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

public class NotificationException extends DomainException {
    public NotificationException(String message, final Notification notification) {
        super(message, notification.getErrors());
    }
}
