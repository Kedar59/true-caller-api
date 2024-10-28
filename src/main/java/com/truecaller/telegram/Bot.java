package com.truecaller.telegram;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.truecaller.controllers.ProfileController;
import com.truecaller.entities.Profile;
import com.truecaller.projections.CallerID;
import com.truecaller.repositories.ProfileRepository;
import com.truecaller.services.OtpService;
import com.truecaller.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private OtpService otpService;
    @Value("${telegram.username}")
    private String username;
    @Value("${telegram.authToken}")
    private String authToken;
    private Logger logger = LoggerFactory.getLogger(ProfileController.class);
    @Override
    public String getBotUsername() {
        return username;
    }
    @Override
    public String getBotToken(){
        return authToken;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        User user = msg.getFrom();
        Long id = user.getId();
        if (msg.hasContact()) {
            String mobileNumber = msg.getContact().getPhoneNumber();
            logger.info("mobilenumber before: "+mobileNumber);
            if (!mobileNumber.startsWith("+")) {
                mobileNumber = "+" + mobileNumber;
            }
            logger.info("mobilenumber after: "+mobileNumber);
            CallerID callerID = extractCallerID(mobileNumber);
            Optional<Profile> requestersProfileOptional = profileService.getProfileByCallerID(callerID.getNumber(),callerID.getCountryCode());
            String confirmationMessage = "";
            if (requestersProfileOptional.isPresent()) {
                Profile requestersProfile = requestersProfileOptional.get();
                String otp = otpService.sendToTelegram(mobileNumber);
                confirmationMessage = String.format("""
                    Thanks! We received your phone number as \s
                    country code : %s \s
                    mobile number : %s \s
                    Your otp is : %s \s""",callerID.getCountryCode(),callerID.getNumber(),otp);
            } else {
                confirmationMessage = String.format("""
                    Thanks! We received your phone number as \s
                    country code : %s \s
                    mobile number : %s \s
                    You are not registerd truecaller user please register""",callerID.getCountryCode(),callerID.getNumber());
            }
            sendText(id, confirmationMessage);
        } else if(msg.isCommand()){
            if(msg.getText().equals("/requestotp")){
                requestPhoneNumber(id);
            }
        }
    }
    public CallerID extractCallerID(String numberStr){
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        CallerID callerID = new CallerID();
        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(numberStr, "");
            callerID.setCountryCode("+"+Integer.toString(numberProto.getCountryCode()));
            callerID.setNumber(Long.toString(numberProto.getNationalNumber()));
            logger.info("Country code: " + callerID.getCountryCode());
            logger.info("National number: " + callerID.getNumber());
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }
        return callerID;
    }
    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
    public void requestPhoneNumber(Long chatId) {
        String answer = "Please share your phone number to receive the OTP:";

        // Create a ReplyKeyboardMarkup to ask for contact
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true); // Optional: adjust size for mobile
        keyboardMarkup.setOneTimeKeyboard(true); // Keyboard will disappear after use

        // Create the button that requests contact information
        KeyboardButton contactButton = new KeyboardButton("Send your phone number");
        contactButton.setRequestContact(true);

        // Add the button to a keyboard row
        KeyboardRow row = new KeyboardRow();
        row.add(contactButton);

        // Add the row to the keyboard
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);

        // Send the message with the custom keyboard
        SendMessage requestMessage = new SendMessage();
        requestMessage.setChatId(chatId.toString());
        requestMessage.setText(answer);
        requestMessage.setReplyMarkup(keyboardMarkup);

        try {
            execute(requestMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}