package br.com.go.notification.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	@Value("${spring.rabbitmq.queue-action}")
	private String queueAction;

	@Value("${spring.rabbitmq.exchange-action}")
	private String exchangeAction;

	@Value("${spring.rabbitmq.routingkey-action}")
	private String routingKeyAction;

	@Value("${spring.rabbitmq.queue-push}")
	private String queuePush;

	@Value("${spring.rabbitmq.exchange-push}")
	private String exchangePush;

	@Value("${spring.rabbitmq.routingkey-push}")
	private String routingKeyPush;

	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Value("${spring.rabbitmq.host}")
	private String host;

	@Bean
	public Queue queueAction() {
		return new Queue(queueAction, true);
	}

	@Bean

	public Exchange exchangeAction() {
		return ExchangeBuilder.directExchange(exchangeAction).durable(true).build();
	}

	@Bean
	public Binding bindingAction() {
		return BindingBuilder
				.bind(queueAction())
				.to(exchangeAction())
				.with(routingKeyAction)
				.noargs();
	}

	@Bean
	public Queue queuePush() {
		return new Queue(queuePush, true);
	}

	@Bean

	public Exchange exchangePush() {
		return ExchangeBuilder.directExchange(exchangeAction).durable(true).build();
	}

	@Bean
	public Binding bindingPush() {
		return BindingBuilder
				.bind(queuePush())
				.to(exchangePush())
				.with(routingKeyPush)
				.noargs();
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
		cachingConnectionFactory.setUsername(username);
		cachingConnectionFactory.setPassword(password);
		return cachingConnectionFactory;
	}

	@Bean
	public MessageConverter messageConverter() {
		ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

		return new Jackson2JsonMessageConverter(mapper);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}

}
