package com.api.v01.NotificationAddressFacadeSystem;

import com.api.v01.NotificationAddressFacadeSystem.data.NotificationLog.NotificationLog;
import com.api.v01.NotificationAddressFacadeSystem.data.NotificationLog.Status;
import com.api.v01.NotificationAddressFacadeSystem.service.NotificationLog.NotificationLogService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final NotificationLogService notificationLogService;

    @Override
    public void run(String... args) {

        notificationLogService.addLog(
                NotificationLog.builder()
                        .notificationId(1L)
                        .status(Status.SUCCESS)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        notificationLogService.addLog(
                NotificationLog.builder()
                        .notificationId(2L)
                        .status(Status.PENDING)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        notificationLogService.addLog(
                NotificationLog.builder()
                        .notificationId(3L)
                        .status(Status.FAILED)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }
}
