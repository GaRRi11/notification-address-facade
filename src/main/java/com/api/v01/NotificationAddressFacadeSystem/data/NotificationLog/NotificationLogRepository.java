package com.api.v01.NotificationAddressFacadeSystem.data.NotificationLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

    @Query("SELECT n.status, COUNT(n) FROM NotificationLog n GROUP BY n.status")
    List<Object[]> getNotificationStatusStatistics();

}
