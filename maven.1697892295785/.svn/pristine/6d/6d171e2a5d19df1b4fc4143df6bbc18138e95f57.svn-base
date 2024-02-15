package com.aashdit.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.lms.model.MessageTemplate;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {
	
	MessageTemplate findByTemplateName(String templateName);

	MessageTemplate findByTemplateId(Long tempRegId);

}
