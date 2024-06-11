package com.generation.blogpessoal.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    public void sendSms(String to, String body) {
        Message.creator(
            new PhoneNumber(to),
            new PhoneNumber(fromPhoneNumber),
            body
        ).create();
    }
}
