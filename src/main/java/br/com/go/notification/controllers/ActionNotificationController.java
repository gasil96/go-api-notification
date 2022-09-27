package br.com.go.notification.controllers;

import br.com.go.notification.dtos.ActionNotificationDTO;
import br.com.go.notification.services.ActionNotificationService;
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
@Tag(name = "Action Notification Management")
@RequestMapping
@RestController
public class ActionNotificationController {

	@Autowired
	private ActionNotificationService actionNotificationService;

	@Operation(summary = "Delete action notification by id")
	@DeleteMapping("/v1/actions/{actionNotificationId}")
	public ResponseEntity<Void> delete(@PathVariable("actionNotificationId") Long actionNotificationId) {
		actionNotificationService.delete(actionNotificationId);

		log.debug("ActionNotificationController.delete(actionNotificationId: {})", actionNotificationId);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Get all action notifications")
	@GetMapping("/v1/actions")
	public ResponseEntity<Page<ActionNotificationDTO>> getAll(Pageable pageable) {
		Page<ActionNotificationDTO> actionNotificationDTOPage = actionNotificationService.getAll(pageable);

		log.debug("ActionNotificationController.getAll(actionNotificationDTOPage: {})", actionNotificationDTOPage);
		return ResponseEntity.ok().body(actionNotificationDTOPage);
	}

}
