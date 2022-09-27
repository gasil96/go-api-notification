package br.com.go.notification.services;

import br.com.go.notification.Utils;
import br.com.go.notification.dtos.ActionMessageDTO;
import br.com.go.notification.dtos.ActionNotificationDTO;
import br.com.go.notification.entities.ActionNotification;
import br.com.go.notification.repositories.ActionNotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ActionNotificationServiceImpl implements ActionNotificationService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ActionNotificationRepository actionNotificationRepository;

	@Override
	public void save(ActionNotificationDTO actionNotificationDTO) {
		ActionNotification actionNotification = mapper.map(actionNotificationDTO, ActionNotification.class);

		actionNotificationRepository.save(actionNotification);
		log.debug("ActionNotificationServiceImpl.create(actionMessageDTO: {})", actionNotificationDTO);
	}

	@Override
	public void delete(Long id) {
		actionNotificationRepository.deleteById(id);
		log.debug("ActionNotificationServiceImpl.delete(id: {})", id);
	}

	@Override
	public Page<ActionNotificationDTO> getAll(Pageable pageable) {
		Page<ActionNotificationDTO> actionNotificationDTOPage = Utils.mapPageBiDirectional(actionNotificationRepository.findAll(pageable),
				ActionNotificationDTO.class);

		log.debug("ActionNotificationServiceImpl.getAll(actionNotificationDTOPage: {})", actionNotificationDTOPage);
		return actionNotificationDTOPage;
	}

}
