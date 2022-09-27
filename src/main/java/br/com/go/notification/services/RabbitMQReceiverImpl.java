package br.com.go.notification.services;

import br.com.go.notification.dtos.ActionMessageRabbitDTO;
import br.com.go.notification.dtos.ActionNotificationDTO;
import br.com.go.notification.dtos.PushMessageDTO;
import br.com.go.notification.dtos.PushMessageRabbitDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQReceiverImpl implements RabbitListenerConfigurer {

	@Autowired
	private ActionNotificationService actionNotificationService;

	@Autowired
	private PushMessageService pushMessageService;

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

	}

	@RabbitListener(queues = "${spring.rabbitmq.queue-action}")
	public void receivedMessageAction(ActionMessageRabbitDTO actionMessageRabbitDTO) {
		ActionNotificationDTO action = builderActionNotification(actionMessageRabbitDTO);

		actionNotificationService.save(action);
		log.debug("RabbitMqReceiver.receivedMessageAction(actionMessageRabbitDTO: {})", actionMessageRabbitDTO);
	}

	@RabbitListener(queues = "${spring.rabbitmq.queue-push}")
	public void receivedMessagePush(PushMessageRabbitDTO pushMessageRabbitDTO) {
		PushMessageDTO pushMessageDTO = builderPushNotification(pushMessageRabbitDTO);

		pushMessageService.save(pushMessageDTO);
		log.debug("RabbitMqReceiver.receivedMessagePush(pushMessageRabbitDTO: {})", pushMessageRabbitDTO);
	}

	private PushMessageDTO builderPushNotification(PushMessageRabbitDTO pushMessageRabbitDTO) {
		return PushMessageDTO.builder()
				.userId(pushMessageRabbitDTO.getUserId())
				.message(pushMessageRabbitDTO.getMessage())
				.created(pushMessageRabbitDTO.getCreated())
				.build();
	}

	private ActionNotificationDTO builderActionNotification(ActionMessageRabbitDTO actionMessageRabbitDTO) {
		return ActionNotificationDTO.builder()
				.content(actionMessageRabbitDTO.getPayload())
				.description(actionMessageRabbitDTO.getDescription())
				.created(actionMessageRabbitDTO.getCreated())
				.build();
	}

}
