package com.truecaller.services.impls;

import com.truecaller.controllers.ApiController;
import com.truecaller.entities.Profile;
import com.truecaller.projections.CallerID;
import com.truecaller.repositories.ProfileRepository;
import com.truecaller.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImplements implements ProfileService {
    @Autowired
    private ProfileRepository repository;
    @Override
    public void saveProfile(Profile profile) {
        repository.save(profile);
    }
    private Logger logger = LoggerFactory.getLogger(ProfileServiceImplements.class);
    @Override
    public Optional<Profile> getProfileByPhoneNumber(String phoneNumber){
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<Profile> getProfileByCallerID(String number,String countryCode){
        logger.info("in profileServiceImplements number:"+number+" countryCode : "+countryCode);
        return repository.findByCallerId(number,countryCode);
    }
}
