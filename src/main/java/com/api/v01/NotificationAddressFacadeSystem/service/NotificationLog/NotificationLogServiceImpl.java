package com.api.v01.NotificationAddressFacadeSystem.service.NotificationLog;

import com.api.v01.NotificationAddressFacadeSystem.data.NotificationLog.NotificationLog;
import com.api.v01.NotificationAddressFacadeSystem.data.NotificationLog.NotificationLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NotificationLogServiceImpl implements NotificationLogService {

    private final NotificationLogRepository notificationLogRepository;

    @Override
    public List<Object[]> getNotificationStatistics() {
        return notificationLogRepository.getNotificationStatusStatistics();
    }


    @Override
    public NotificationLog addLog(NotificationLog log) {
        return notificationLogRepository.save(log);
    }
}
