package com.api.v01.NotificationAddressFacadeSystem.data.NotificationLog;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationLogDTO {

    @NotBlank(message = "Notification id cannot be blank")
    private Long notificationId;

    @NotBlank(message = "Status cannot be blank")
    private Status status;

    public NotificationLog fromDTO(NotificationLogDTO dto) {
        return NotificationLog.builder()
                .notificationId(dto.getNotificationId())
                .status(dto.getStatus())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
