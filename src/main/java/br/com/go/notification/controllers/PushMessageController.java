package br.com.go.notification.controllers;

import br.com.go.notification.dtos.PushMessageDTO;
import br.com.go.notification.services.PushMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Push Message Management")
@RequestMapping
@RestController
public class PushMessageController {

	@Autowired
	private PushMessageService pushMessageService;

	@Operation(summary = "Get push message by userId")
	@GetMapping("/v1/messages/users/{userId}")
	public ResponseEntity<Page<PushMessageDTO>> getByUserId(@PathVariable("userId") String userId, Pageable pageable) {
		Page<PushMessageDTO> pushMessageDTOPage = pushMessageService.getByUserId(userId, pageable);

		log.debug("PushMessageController.getByUserId(pushMessageId: {})", pushMessageDTOPage);
		return ResponseEntity.ok().body(pushMessageDTOPage);
	}

	@Operation(summary = "Delete push message by id")
	@DeleteMapping("/v1/messages/{pushMessageId}")
	public ResponseEntity<Void> delete(@PathVariable("pushMessageId") Long pushMessageId) {
		pushMessageService.delete(pushMessageId);

		log.debug("PushMessageController.delete(pushMessageId: {})", pushMessageId);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Get all messages")
	@GetMapping("/v1/messages")
	public ResponseEntity<Page<PushMessageDTO>> getAll(Pageable pageable) {
		Page<PushMessageDTO> pushMessageDTOPage = pushMessageService.getAll(pageable);

		log.debug("PushMessageController.getAll(pushMessageDTOPage: {})", pushMessageDTOPage);
		return ResponseEntity.ok().body(pushMessageDTOPage);
	}

}
