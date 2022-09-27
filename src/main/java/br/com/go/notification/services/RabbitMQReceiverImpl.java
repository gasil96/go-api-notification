package br.com.go.notification.services;

import br.com.go.notification.dtos.ActionMessageDTO;
import br.com.go.notification.dtos.ActionNotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Slf4j
@Component
public class RabbitMQReceiverImpl implements RabbitListenerConfigurer {

	@Autowired
	private ActionNotificationService actionNotificationService;

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

	}

	@RabbitListener(queues = "${spring.rabbitmq.queue-action}")
	public void receivedMessageAction(ActionMessageDTO actionMessageDTO) {
		ActionNotificationDTO action = builderActionNotification(actionMessageDTO);

		actionNotificationService.save(action);
		log.debug("RabbitMqReceiver.receivedMessageAction(actionMessageDTO: {})", actionMessageDTO);
	}

	private ActionNotificationDTO builderActionNotification(ActionMessageDTO actionMessageDTO) {
		return ActionNotificationDTO.builder()
				.content(actionMessageDTO.getPayload())
				.description(actionMessageDTO.getDescription())
				.created(ZonedDateTime.now())
				.build();
	}

}
