package br.com.go.notification.services;

import br.com.go.notification.Utils;
import br.com.go.notification.dtos.PushMessageDTO;
import br.com.go.notification.entities.PushMessage;
import br.com.go.notification.repositories.PushMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PushMessageServiceImpl implements PushMessageService {

	@Autowired
	private PushMessageRepository pushMessageRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(PushMessageDTO pushMessageDTO) {
		PushMessage pushMessage = mapper.map(pushMessageDTO, PushMessage.class);

		pushMessageRepository.save(pushMessage);
		log.debug("PushMessageServiceImpl.create(pushMessageDTO:{} )", pushMessageDTO);
	}

	@Override
	public void delete(Long id) {
		pushMessageRepository.deleteById(id);
		log.debug("PushMessageServiceImpl.delete(id:{} )", id);
	}

	@Override
	public Page<PushMessageDTO> getAll(Pageable pageable) {
		Page<PushMessageDTO> pushMessageDTOPage = Utils.mapPageBiDirectional(
				pushMessageRepository.findAll(pageable), PushMessageDTO.class);

		log.debug("PushMessageServiceImpl.getAll(pushMessageDTOPage: {})", pushMessageDTOPage);
		return pushMessageDTOPage;
	}

	@Override
	public Page<PushMessageDTO> getByUserId(String userId, Pageable pageable) {
		Page<PushMessageDTO> pushMessageDTOPage = Utils.mapPageBiDirectional(
				pushMessageRepository.findByUserId(userId, pageable), PushMessageDTO.class);

		log.debug("PushMessageServiceImpl.getByUserId(pushMessageDTOPage: {})", pushMessageDTOPage);
		return pushMessageDTOPage;
	}

}
