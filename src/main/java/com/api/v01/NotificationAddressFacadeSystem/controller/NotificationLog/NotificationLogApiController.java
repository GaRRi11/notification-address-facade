package com.api.v01.NotificationAddressFacadeSystem.controller.NotificationLog;

import com.api.v01.NotificationAddressFacadeSystem.data.NotificationLog.NotificationLog;
import com.api.v01.NotificationAddressFacadeSystem.service.NotificationLog.NotificationLogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@AllArgsConstructor
public class NotificationLogApiController {

    private final NotificationLogService notificationLogService;

    @GetMapping("/statistics")
    public ResponseEntity<List<Object[]>> getNotificationStatistics() {
        List<Object[]> stats = notificationLogService.getNotificationStatistics();
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/add")
    public ResponseEntity<NotificationLog> addLog(@Valid @RequestBody NotificationLog log) {
        NotificationLog savedLog = notificationLogService.addLog(log);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLog);
    }
}
