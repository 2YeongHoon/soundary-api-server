package com.domain.notification.infrastructure;

import com.domain.notification.domain.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<Notification, Long> {

    List<Notification> findNotificationByUserId(Long userId);

}
