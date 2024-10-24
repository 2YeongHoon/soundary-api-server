package com.domain.notification.service.port;

import com.domain.notification.domain.Notification;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository {

    void save(Notification notification);

    void saveAll(List<Notification> notifications);

    void delete(Long notifyId);

    List<Notification> findByUserId(Long userId);

    Optional<Notification> findById(Long notifyId);

}
