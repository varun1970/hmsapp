package com.hmsapp.Service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPService  {

    // Temporary store for OTPs (key: phone number, value: OTPDetails)
    private final Map<String, OTPDetails> otpStore = new HashMap<>();

    // OTP expiration time (5 minutes in milliseconds)
    private static final long OTP_VALIDITY_DURATION = 5 * 60 * 1000;

    public String generateOtp(String phoneNumber) {
        // Generate a 6-digit OTP
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        // Store the OTP along with the current timestamp
        otpStore.put(phoneNumber, new OTPDetails(otp, System.currentTimeMillis()));

        // Simulate sending OTP
        System.out.println("Generated OTP for " + phoneNumber + ": " + otp);

        return otp;
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
        OTPDetails otpDetails = otpStore.get(phoneNumber);

        if (otpDetails != null && otpDetails.getOtp().equals(otp) &&
                java.time.Duration.ofMillis(System.currentTimeMillis() - otpDetails.getTimestamp()).toMinutes() <= 5) {
            otpStore.remove(phoneNumber);
            return true;
        }


        // OTP is invalid or expired
        return false;
    }

    // Inner class to store OTP and timestamp
    private static class OTPDetails {
        private final String otp;
        private final long timestamp;

        public OTPDetails(String otp, long timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }

        public String getOtp() {
            return otp;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}

