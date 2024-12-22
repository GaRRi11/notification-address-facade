package com.api.v01.NotificationAddressFacadeSystem.service.NotificationLog;

import com.api.v01.NotificationAddressFacadeSystem.data.NotificationLog.NotificationLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationLogService {

    List<Object[]> getNotificationStatistics();

    List<NotificationLog> getLogsByCustomerId(Long customerId);

    NotificationLog addLog(NotificationLog log);

}
