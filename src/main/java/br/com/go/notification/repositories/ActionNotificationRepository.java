package br.com.go.notification.repositories;

import br.com.go.notification.entities.ActionNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionNotificationRepository extends JpaRepository<ActionNotification, Long> {

}
