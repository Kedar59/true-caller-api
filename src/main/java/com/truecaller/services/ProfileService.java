package com.truecaller.services;

import com.truecaller.entities.Profile;
import com.truecaller.projections.CallerID;

import java.util.Optional;

public interface ProfileService {
    void saveProfile(Profile profile);

    Optional<Profile> getProfileByPhoneNumber(String phoneNumber);
    Optional<Profile> getProfileByCallerID(String number,String CountryCode);
}
