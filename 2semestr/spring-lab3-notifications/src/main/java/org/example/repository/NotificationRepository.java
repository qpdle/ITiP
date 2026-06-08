package org.example.repository;

import org.example.model.entity.Notification;
import org.example.model.enums.NotificationChannel;
import org.example.model.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByStatus(NotificationStatus status);

    List<Notification> findByChannel(NotificationChannel channel);

    List<Notification> findByRecipientId(Long recipientId);

    // 6.1. Запрос по нескольким параметрам
    List<Notification> findByStatusAndChannel(NotificationStatus status, NotificationChannel channel);

    // 6.2. Сортировка в имени метода
    List<Notification> findByStatusOrderByCreatedAtAsc(NotificationStatus status);

    // 6.3. JPQL-запрос
    @Query("""
           select n
           from Notification n
           where n.status = :status
           and n.channel = :channel
           """)
    List<Notification> findByStatusAndChannelCustom(@Param("status") NotificationStatus status,
                                                    @Param("channel") NotificationChannel channel);

    // 6.4 Native SQL-запрос
    @Query(value = """
       select *
       from notifications
       where status = :status
       and channel = :channel
       """, nativeQuery = true)
    List<Notification> findNativeByStatusAndChannel(@Param("status") String status,
                                                    @Param("channel") String channel);
}