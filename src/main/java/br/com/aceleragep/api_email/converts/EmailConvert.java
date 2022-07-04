package br.com.aceleragep.api_email.converts;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aceleragep.api_email.Entitys.EmailEntity;
import br.com.aceleragep.api_email.dto.input.EmailInput;
import br.com.aceleragep.api_email.dto.output.EmailOutput;

@Component
public class EmailConvert {	
	
	@Autowired
	ModelMapper modelMapper;
		
	public EmailEntity inputToEntity(@Valid EmailInput emailInput) {
		return modelMapper.map(emailInput, EmailEntity.class);
	}

	public EmailOutput entityToOutput(EmailEntity emailEnviado) {
		return modelMapper.map(emailEnviado, EmailOutput.class);
	}

}
