package br.com.aceleragep.api_email.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailInput {
	@NotBlank
	private String owenrRef;
	@NotBlank
	@Email
	private String emailTo;
}
