package br.com.go.notification.services;

import br.com.go.notification.dtos.PushMessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PushMessageService {

	void save(PushMessageDTO pushMessageDTO);

	void delete(Long id);

	Page<PushMessageDTO> getAll(Pageable pageable);

	Page<PushMessageDTO> getByUserId(String userId, Pageable pageable);

}
