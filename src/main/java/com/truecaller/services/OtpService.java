package com.truecaller.services;

import com.truecaller.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {
    @Autowired
    private TwilioConfig twilioConfig;
    // This map will store OTPs and their expiration time temporarily in memory
    private ConcurrentHashMap<String, OtpData> otpStorage = new ConcurrentHashMap<>();
    private static final int OTP_EXPIRATION_MINUTES = 5;
    private static final long OTP_EXPIRATION_MILLIS = OTP_EXPIRATION_MINUTES * 60 * 1000;

    //generate OTP
    public String generateOtp(){
        int otp = (int)(Math.random()*1000000);
        String otpS = String.format("%6d",otp);
        if(otpS.length()==5) otpS+="0";
        return otpS;
    }
    // send otp
    public String sendToTelegram(String mobileNumber){
        String otp = generateOtp();
        long expirationTime = System.currentTimeMillis() + OTP_EXPIRATION_MILLIS;
        OtpData otpData = new OtpData(otp, expirationTime);
        otpStorage.put(mobileNumber, otpData);
        return otp;
    }
    public String sendToPhone(String mobileNumber){
        String otp = generateOtp();
        PhoneNumber reciepientPhoneNumber = new PhoneNumber(mobileNumber);
        PhoneNumber senderPhoneNumber = new PhoneNumber(twilioConfig.getPhoneNumber());
        String messageBody = "your one time password(otp) is : "+otp;
        Message message = Message.creator(reciepientPhoneNumber, senderPhoneNumber, messageBody).create();
        // Store OTP in-memory with expiration time (using epoch time in milliseconds)
        long expirationTime = System.currentTimeMillis() + OTP_EXPIRATION_MILLIS;
        OtpData otpData = new OtpData(otp, expirationTime);
        otpStorage.put(mobileNumber, otpData);
        return "otp sent successfully to " + mobileNumber;
    }
    // validate OTP
    public boolean validateOtp(String mobileNumber,String otp){
        OtpData otpData = otpStorage.get(mobileNumber);

        // Check if OTP exists and hasn't expired
        if (otpData != null && otpData.getOtp().equals(otp)) {
            if (System.currentTimeMillis() < otpData.getExpirationTime()) {
                // OTP is valid
                return true;
            } else {
                // OTP expired
                otpStorage.remove(mobileNumber); // Clean up expired OTP
            }
        }

        return false; // OTP is invalid or expired
    }
    private static class OtpData {
        private String otp;
        private long expirationTime; // Epoch time in milliseconds

        public OtpData(String otp, long expirationTime) {
            this.otp = otp;
            this.expirationTime = expirationTime;
        }

        public String getOtp() {
            return otp;
        }

        public long getExpirationTime() {
            return expirationTime;
        }
    }
}
