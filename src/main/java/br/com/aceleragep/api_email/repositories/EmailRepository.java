package br.com.aceleragep.api_email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aceleragep.api_email.Entitys.EmailEntity;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

}
