package br.com.aceleragep.api_email.dto.output;

import java.time.LocalDateTime;

import br.com.aceleragep.api_email.Entitys.StatusEmail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailOutput {
	private Long id;
	private String owenrRef;
	private String emailFrom;
	private String emailTo;
	private String subject;
	private LocalDateTime sendDateEmail;
	private StatusEmail statusEmail; 
}
