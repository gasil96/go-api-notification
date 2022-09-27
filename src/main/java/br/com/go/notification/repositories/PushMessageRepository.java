package br.com.go.notification.repositories;

import br.com.go.notification.entities.PushMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PushMessageRepository extends JpaRepository<PushMessage, Long> {

	Page<PushMessage> findByUserId(String userId, Pageable pageable);

}
