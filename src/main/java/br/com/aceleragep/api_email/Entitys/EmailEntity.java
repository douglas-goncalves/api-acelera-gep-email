package br.com.aceleragep.api_email.Entitys;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="TB_EMAIL")
public class EmailEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "owner_ref")
	private String owenrRef;
	@Column(name = "email_from")
	private String emailFrom;
	@Column(name = "email_to")
	private String emailTo;
	@Column(name = "subject")
	private String subject;
	@Column(name = "send_date_email")
	private LocalDateTime sendDateEmail;
	@Column(name = "status_email")
	@Enumerated(EnumType.STRING)
	private StatusEmail statusEmail; 
}
