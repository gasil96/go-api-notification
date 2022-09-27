package br.com.go.notification.services;

import br.com.go.notification.dtos.ActionNotificationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActionNotificationService {

	void save(ActionNotificationDTO actionNotificationDTO);

	void delete(Long id);

	Page<ActionNotificationDTO> getAll(Pageable pageable);

}
