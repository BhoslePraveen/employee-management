package com.codemind.whirlpool.employee_management.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public interface EmailService {

	String sendEmail(String toMail, String subject, String body);

}
