package com.codemind.whirlpool.employee_management.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Value("${spring.mail.username}")
	private String fromEmail;

	private final JavaMailSender mailSender;

	public EmailServiceImpl(JavaMailSender javaMailSender) {
		this.mailSender = javaMailSender;
	}

	@Override
	public String sendEmail(String toMail, String subject, String body) {
		try {
			log.info("In side send mail method");
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setFrom(fromEmail, "Praveen");
			helper.setTo(toMail);
			helper.setSubject(subject);
			helper.setText(body, true);
			log.info("Mail Creation process Completed");
			mailSender.send(message);
			log.info("Mail sent!!");
			return "Mail Sent Successfully";
		} catch (Exception e) {
			log.error("Exception Occured");
			e.printStackTrace();
			return "Mail rejected";
		}
	}

}
