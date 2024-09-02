package com.digitalcoffee.order.service;

import com.digitalcoffee.order.model.Notification;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface NotificationServiceClient {

    @PostMapping(value = "/notification/")
    Notification sendNotification(@RequestBody Notification orderDetails);

}


@Component
class NotificationServiceClientFallback implements NotificationServiceClient{

    @Override
    public Notification sendNotification(Notification orderDetails) {
        return new Notification();
    }
}