package com.truecaller.controllers;

import com.mongodb.DuplicateKeyException;
import com.truecaller.entities.Contact;
import com.truecaller.entities.Profile;
import com.truecaller.exceptions.ProfileNotFoundException;
import com.truecaller.projections.CallerID;
import com.truecaller.projections.ContactDTO;
import com.truecaller.services.ContactService;
import com.truecaller.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.truecaller.error.ErrorResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ContactService contactService;
    @PostMapping("/addContact")
    public ResponseEntity<?> addContact(@RequestBody ContactDTO contactDTO){
        String ownersNumber = contactDTO.getOwner().getNumber();
        String ownersCountryCode = contactDTO.getOwner().getCountryCode();
        String contactsNumber = contactDTO.getContact().getNumber();
        String contactsCountryCode = contactDTO.getContact().getCountryCode();
        Profile owner = profileService.getProfileByCallerID(ownersNumber,ownersCountryCode).orElseThrow(() ->
                new ProfileNotFoundException("Owner with contact number : "+ownersCountryCode+" "+ownersNumber+
                        " dosent have a truecaller account. Make a truecaller account from the App"));
        if(!owner.isVerified()){
            ErrorResponse profileNotVerified = new ErrorResponse(LocalDateTime.now(),
                    "Your Profile associated with number : "+ownersCountryCode+" "+ownersNumber+
                            " is now verified please verify from truecaller App","Profile not verified");
            return new ResponseEntity<>(profileNotVerified, HttpStatus.EXPECTATION_FAILED);
        }
        Optional<Profile> contact = profileService.getProfileByCallerID(contactDTO.getContact().getNumber(),contactDTO.getContact().getCountryCode());
        Profile contactProf;
        if(contact.isPresent()){
            contactProf = contact.get();
        } else {
            Profile contactProfile = new Profile(contactsNumber,contactsCountryCode,0,0);
            contactProf = profileService.saveProfile(contactProfile);
        }
        Contact newContact = contactService.createContact(owner,contactProf);
        return ResponseEntity.ok(newContact);
    }
    @GetMapping("/getOwnersContacts/{ownersID}")
    public ResponseEntity<?> getOwnersContacts(@PathVariable String ownersID){
        List<Profile> contacts = contactService.findOwnersContacts(ownersID);
        return ResponseEntity.ok(contacts);
    }
}
