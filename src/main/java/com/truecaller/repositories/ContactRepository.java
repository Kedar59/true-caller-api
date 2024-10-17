package com.truecaller.repositories;

import com.truecaller.entities.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends MongoRepository<Contact,String> {
    @Query("{ 'owner.$id': ?0, 'contact.$id': ?1 }")
    Optional<Contact> findByOwnerAndContactIds(String ownerId, String contactId);

    @Query("{ 'owner.$id': ?0 }")
    List<Contact> findContactsByOwnerId(String ownerId);
    List<Contact> findByOwner_Id(String ownerId);
}
