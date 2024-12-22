package com.api.v01.NotificationAddressFacadeSystem.data.Notification;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {

    private Long customerId;

    private Long addressId;

    private String text;

    private Notification fromDTO(NotificationDTO notificationDTO) {
        Notification notification = Notification.builder()
                .customerId(notificationDTO.getCustomerId())
                .addressId(notificationDTO.getAddressId())
                .text(notificationDTO.getText())
                .createdAt(LocalDateTime.now())
                .build();
        return notification;
    }
}
