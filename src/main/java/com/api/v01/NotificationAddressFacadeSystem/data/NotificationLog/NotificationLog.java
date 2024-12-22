package com.api.v01.NotificationAddressFacadeSystem.data.NotificationLog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "notification_id")
    private Long notificationId;

    @Column(nullable = false,name = "status")
    private boolean status;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

}
