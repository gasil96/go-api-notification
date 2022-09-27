package br.com.go.notification.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionNotificationDTO implements Serializable {

	private static final long serialVersionUID = -1411332233515716999L;

	private Long id;
	private String description;
	private String content;
	private ZonedDateTime created;

}
