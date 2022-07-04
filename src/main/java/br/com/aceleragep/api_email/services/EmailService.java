package br.com.aceleragep.api_email.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.aceleragep.api_email.Entitys.EmailEntity;
import br.com.aceleragep.api_email.Entitys.StatusEmail;
import br.com.aceleragep.api_email.repositories.EmailRepository;
import lombok.Getter;
import lombok.Setter;

@Service
public class EmailService {

	@Value("${mail.host}")
	private String host;
	@Value("${mail.port}")
	private int port;
	@Value("${mail.username}")
	private String username;
	@Value("${mail.password}")
	private String password;
	@Value("${mail.smtp.auth}")
	private String auth;
	@Value("${mail.smtp.starttls.enable}")
	private String starttls;
	@Value("${mail.smtp.startlls_required}")
	private String startllsRequired;

	@Autowired
	private EmailRepository emailRepository;

	@SuppressWarnings("finally")
	public EmailEntity sendEmail(EmailEntity emailEntity) {
		HtmlBody htmlEmail = htmlEmailCadastro(emailEntity);
		
		emailEntity.setSendDateEmail(LocalDateTime.now());
		emailEntity.setEmailFrom(this.username);
		emailEntity.setSubject(htmlEmail.getMsg());

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", this.auth);
		mailProperties.put("mail.smtp.starttls", this.starttls);
		mailProperties.put("mail.smtp.starttls.required", this.startllsRequired);
		mailProperties.put("mail.smtp.host", this.host);
		mailProperties.put("mail.smtp.port", this.port);
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setUsername(this.username);
		sender.setPassword(this.password);
		sender.setJavaMailProperties(mailProperties);


		try {
			MimeMessage mimeMessage = sender.createMimeMessage();
			mimeMessage.setHeader("Content-Type", "text/html");

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			
			helper.setTo(emailEntity.getEmailTo());
			helper.setText(this.htmlEmailCadastro(emailEntity).getBody(), true);
			helper.setFrom(emailEntity.getEmailFrom());
			helper.setSubject(emailEntity.getSubject());

			sender.send(mimeMessage);

			emailEntity.setStatusEmail(StatusEmail.SENT);
		} catch (MailException ex) {
			emailEntity.setStatusEmail(StatusEmail.ERROR);
		} finally {
			return emailRepository.save(emailEntity);
		}
	}
	
	public HtmlBody htmlEmailCadastro(EmailEntity emailEntity) {
		
		HtmlBody htmlBody = new HtmlBody();
		
		htmlBody.setMsg("Este email é um email de teste e para estudo");
		htmlBody.setBody(String.format(
				 "<div style='padding:32px; text-align:center; border: 1px rgba(0,0,0,.3); border-radius:32px ; background-color:#cccc55'>"
							+"<style type='text/css'> "
								+ "a:active { color:#000000; background-color:#ffffff} "
								+ "a:hover { font-weight: bold }"
							+ "</style>"
							+"<div>"
								+"<img src='https://www.aceleragep.com.br/src/img/acelera-logo-redimensionado.png'>"
							+"</div>"
								
							+"<div>"	
								+"<p style='color: #ffffff; font-size:14pt; font-weight: bolder; '>"
									+ "%s Seja bem vindo senhor(a) %s."
								+ "</p>"
							+"<div>"
								
							+"<div>"
								+"<p style='font-weight: bolder;'>"
									+ "Estamos aqui para avisar que seu cadastrado em nosso sistema já está pronto."
								+ "</p>"
							+"</div>"
							
							+"<div>"
								+"<p style='color: #0000ff;'>"
									+ "Venha conhecer nosso website, click no botão abaixo:"
								+ "</p>"
							+"</div>"
								
							+"<div>"
								+"<a href='https://www.aceleragep.com.br'>"
								+ 	"<input type='button'value='Visite Nosso Site'>"
								+ "</a>"
							+"</div>"
								
							+"<div>"
								+"<p>Atenciosamente Equipe Acelera G&P, acelerando junto com você.</p>"
							+"</div>"
								
						+"</div>"
							
					,whatDayPartIsNow()
					,emailEntity.getOwenrRef()
					));
		return htmlBody;	
	}
	
	public String whatDayPartIsNow() {
		LocalTime localTime = LocalTime.now();
		int hours = localTime.getHour();
		
		if(hours <=11) return ("Bom Dia, ");
		else if(hours <=19) return ("Boa Tarde, ");
		else if(hours <= 23) return ("Boa Noite, ");
		else return "";
	}
}

@Getter
@Setter
class HtmlBody{
	private String msg;
	private String body;
}






