package com.truecaller.services.impls;

import com.truecaller.entities.Profile;
import com.truecaller.repositories.ProfileRepository;
import com.truecaller.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImplements implements ProfileService {
    @Autowired
    private ProfileRepository repository;
    @Override
    public void saveProfile(Profile profile) {
        repository.save(profile);
    }
}
