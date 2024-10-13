package com.truecaller.controllers;

import com.truecaller.entities.Profile;
import com.truecaller.entities.SpamReport;
import com.truecaller.error.ErrorResponse;
import com.truecaller.services.ProfileService;
import com.truecaller.services.SpamReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/spam")
public class SpamReportController {
    @Autowired
    private SpamReportService spamReportService;
    @Autowired
    private ProfileService profileService;
    private Logger logger = LoggerFactory.getLogger(ProfileController.class);
    @PostMapping("/reportSpam")
    public ResponseEntity<SpamReport> addSpamReport(@RequestBody SpamReport spamReport){
        logger.info("Here in add spam report"+spamReport);
        Optional<Profile> spammerProfileOptional = profileService.getProfileByCallerID(spamReport.getSpammerNumber(), spamReport.getSpammerCountryCode());
        if (spammerProfileOptional.isPresent()) {
            Profile spammerProfile = spammerProfileOptional.get();
            profileService.updateSpamersProfile(spammerProfile,spamReport);
        } else {
            profileService.createSpammerProfile(spamReport);
        }
        spamReportService.saveSpamReport(spamReport);
        return ResponseEntity.ok(spamReport);
    }
}
