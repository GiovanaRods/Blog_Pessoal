package com.generation.blogpessoal.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.service.SmsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sms")
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class SmsController {

    private SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send")
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        smsService.sendSms(smsRequest.getTo(), smsRequest.getBody());
    }

    public static class SmsRequest {
        private String to;
        
        private String body;
        
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
    }
}

