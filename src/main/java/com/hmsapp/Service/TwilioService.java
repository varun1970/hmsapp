package com.hmsapp.Service;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    @Value("${twilio.whatsappNumber}")
    private String twilioWhatsAppNumber;

    public void sendSms(String toPhoneNumber, String messageBody) {
        // Initialize Twilio with your Account SID and Auth Token
        Twilio.init(accountSid, authToken);

        // Send the SMS message
        Message message = Message.creator(
                        new PhoneNumber(toPhoneNumber),    // To phone number
                        new PhoneNumber(twilioPhoneNumber), // From Twilio phone number
                        messageBody)                        // SMS content
                .create();

        System.out.println("Message SID: " + message.getSid());
    }



    public String sendWhatsAppMessage(String to, String messageContent) {
        Twilio.init(accountSid, authToken);

        try {
            // Send WhatsApp message
            Message message = Message.creator(
                    new PhoneNumber("whatsapp:" + to),  // Recipient's WhatsApp number
                    new PhoneNumber("whatsapp:" + twilioWhatsAppNumber), // Twilio WhatsApp number (Sandbox or your verified number)
                    messageContent                       // WhatsApp message content
            ).create();

            return "WhatsApp message sent successfully! Message SID: " + message.getSid();
        } catch (Exception e) {
            return "Error sending WhatsApp message: " + e.getMessage();
        }
    }

}