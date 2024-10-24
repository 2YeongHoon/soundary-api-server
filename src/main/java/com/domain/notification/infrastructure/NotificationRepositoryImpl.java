package com.domain.notification.infrastructure;

import com.domain.notification.domain.Notification;
import com.domain.notification.service.port.NotificationRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationJpaRepository jpaRepository;

    @Override
    public void save(Notification notification) {
        jpaRepository.save(notification);
    }

    @Override
    public void saveAll(List<Notification> notifications) {
        jpaRepository.saveAll(notifications);
    }

    @Override
    public void delete(Long notifyId) {
        jpaRepository.deleteById(notifyId);
    }

    @Override
    public List<Notification> findByUserId(Long userId) {
        return jpaRepository.findNotificationByUserId(userId);
    }

    @Override
    public Optional<Notification> findById(Long notifyId) {
        return jpaRepository.findById(notifyId);
    }

}
