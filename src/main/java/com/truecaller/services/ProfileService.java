package com.truecaller.services;

import com.truecaller.entities.Profile;
import com.truecaller.entities.SpamReport;
import com.truecaller.projections.CallerID;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Profile saveProfile(Profile profile);

    Optional<Profile> getProfileByPhoneNumber(String phoneNumber);
    Optional<Profile> getProfileByCallerID(String number,String CountryCode);

    List<Profile> searchProfilesByName(String partialName);
    void updateSpamersProfile(Profile spammer, SpamReport spamReport);
    void createSpammerProfile(SpamReport spamReport);

}
