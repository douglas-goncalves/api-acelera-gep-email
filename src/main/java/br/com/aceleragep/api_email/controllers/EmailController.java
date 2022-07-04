package br.com.aceleragep.api_email.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aceleragep.api_email.Entitys.EmailEntity;
import br.com.aceleragep.api_email.converts.EmailConvert;
import br.com.aceleragep.api_email.dto.input.EmailInput;
import br.com.aceleragep.api_email.dto.output.EmailOutput;
import br.com.aceleragep.api_email.services.EmailService;

@RestController
@RequestMapping("api/emails")
public class EmailController {
	
	@Autowired
	private EmailConvert emailConvert;
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sending-email")
	public ResponseEntity<EmailOutput> sendingEmail(@RequestBody @Valid EmailInput emailInput, UriComponentsBuilder uriBuild) {
		EmailEntity emailEntity = emailConvert.inputToEntity(emailInput);
		EmailEntity emailEnviado = emailService.sendEmail(emailEntity);
		EmailOutput emailOutput=  emailConvert.entityToOutput(emailEnviado);
		
		URI uri = uriBuild.path("api/email/sending-email/{id}").buildAndExpand(emailEntity.getId()).toUri();
		return ResponseEntity.created(uri).body(emailOutput);
	}

}
