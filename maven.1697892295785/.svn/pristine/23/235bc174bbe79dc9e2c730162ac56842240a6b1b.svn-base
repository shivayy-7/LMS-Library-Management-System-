package com.aashdit.lms.utils;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.framework.core.util.MailServiceUtil;
import com.aashdit.lms.model.MailQueue;
import com.aashdit.lms.repository.MailQueueRepository;


@Component()
public class EmailSendTask {

	@Autowired
	private MailQueueRepository mailRepository;

	private static final Logger logger = Logger.getLogger(EmailSendTask.class);

	@Scheduled(fixedRate = 60000) // Every 1 Min
	private void markForProcessiong() {
		sendEmail();
	}

	private void sendEmail() {

		try {
			List<MailQueue> queuedLst = mailRepository.findByStatus("QUEUED");
			if (queuedLst.size() > 0) {
				for (MailQueue item : queuedLst) {
					MailQueue queue = mailRepository.findById(item.getMailQueueId()).get();
					if(Optional.ofNullable(queue).isPresent()) {
						queue.setStatus("PROCESSING");
						queue.setIsActive(true);
						mailRepository.saveAndFlush(queue);
					}
				}
			}

			// Update the selected items to processing

			ServiceOutcome<String> isEmailed = null;

			List<MailQueue> processedLst = mailRepository.findByStatus("PROCESSING");

			if (processedLst.size() < 1) {
				return;
			}

			for (MailQueue queued : processedLst) {

				try {

					isEmailed = MailServiceUtil.sendMail(queued.getBody(), queued.getSubject(), queued.getMailTo(),
							queued.getMailFrom(), queued.getBodyType());

					if (isEmailed.getData().equals("SUCCESS")) {
						queued.setStatus("SENT");
						mailRepository.save(queued);
						logger.debug("Email Sent Succussfully");

					} else {
						queued.setStatus("FAILED");
						queued.setFailureReason(isEmailed.getMessage());
						mailRepository.save(queued);

						logger.debug("Email Sending Failed");
					}
				} catch (Exception e) {
					queued.setStatus("FAILED");
					queued.setFailureReason(e.getMessage());

					mailRepository.save(queued);

					logger.error(e.getMessage());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}

	}

//	@Scheduled(fixedRate = 6000)
//	public void printHello() {
//		System.out.println("@@@@@@@@@@@@@@@@@@HELLO@@@@@@@@@@@@@@@@");
//	}
}
