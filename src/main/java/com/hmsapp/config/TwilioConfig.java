//package com.hmsapp.config;
//
//
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import com.twilio.Twilio;
//
//@Configuration
//public class TwilioConfig {
//
//    @Value("${twilio.account.sid}")
//    private String accountSid;
//
//    @Value("${twilio.auth.token}")
//    private String authToken;
//
//    @Getter
//    @Value("${twilio.phone.number}")
//    private String fromPhoneNumber;
//
//    public void initTwilio() {
//        Twilio.init(accountSid, authToken);
//    }
//}
//
