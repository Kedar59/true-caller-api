package com.truecaller.controllers;

import com.truecaller.entities.Profile;
import com.truecaller.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/registerProfile")
    public ResponseEntity<Profile> registerProfile(@RequestBody Profile profile){
        profileService.saveProfile(profile);
        return ResponseEntity.ok(profile);
    }
}
