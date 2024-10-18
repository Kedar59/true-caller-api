package com.truecaller;

import com.truecaller.config.TwilioConfig;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TruecallerApplication {
    @Autowired
    private TwilioConfig twilioConfig;
    @PostConstruct
    public void setup(){
        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }
	public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "secrets");
        SpringApplication.run(TruecallerApplication.class, args);
	}

}
