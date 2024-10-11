package com.truecaller.controllers;

import com.truecaller.entities.Profile;
import com.truecaller.exceptions.ProfileNotFoundException;
import com.truecaller.projections.CallerID;
import com.truecaller.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ProfileService profileService;

    private Logger logger = LoggerFactory.getLogger(ApiController.class);

    @PostMapping("/registerProfile")
    public ResponseEntity<Profile> registerProfile(@RequestBody Profile profile){
        profileService.saveProfile(profile);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/getProfile")
    @ResponseBody
    public ResponseEntity<?> getProfile(@ModelAttribute CallerID callerId){
        logger.info("In api controller"+callerId.toString());
        Profile profile = profileService.getProfileByCallerID(callerId.getNumber(),callerId.getCountryCode()).
                orElseThrow(() -> new ProfileNotFoundException("Profile with phone number : " + callerId.getCountryCode()
                        + " " + callerId.getNumber() + " not found."));
        return ResponseEntity.ok(profile);
    }
}
