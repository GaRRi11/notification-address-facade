package com.api.v01.NotificationAddressFacadeSystem.service.NotificationLog;

import com.api.v01.NotificationAddressFacadeSystem.data.NotificationLog.NotificationLog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NotificationLogService {

    List<Object[]> getNotificationStatistics();

    NotificationLog addLog(NotificationLog log);

}
