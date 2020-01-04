package com.chat.messango.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.chat.messango.model.Users;

public class EmailServiceImp implements EmailService {

	@Autowired
	 private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(Users user) {
	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(user.getEmail());
	        msg.setSubject("Messango Forgot Password");
	        msg.setText("Hi "+user.getFirstname()+", \n This is your Messango password:"+user.getPassword());
	        javaMailSender.send(msg);
	}

}
