package com.truecaller.repositories;

import com.truecaller.entities.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile,String> {

}
