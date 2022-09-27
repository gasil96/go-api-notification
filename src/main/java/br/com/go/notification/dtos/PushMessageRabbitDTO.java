package br.com.go.notification.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushMessageRabbitDTO {

	private String userId;
	private String message;
	private ZonedDateTime created;

}
