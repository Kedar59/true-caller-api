package com.truecaller;

import com.truecaller.config.TwilioConfig;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

//@EnableConfigurationProperties(BotConfig.class)
@SpringBootApplication
public class TruecallerApplication {
    @Autowired
    private TwilioConfig twilioConfig;
    @PostConstruct
    public void setup() throws TelegramApiException {
        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }
	public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "secrets");
        SpringApplication.run(TruecallerApplication.class, args);
	}

}
