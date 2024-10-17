package com.truecaller.services;

import com.truecaller.entities.Contact;
import com.truecaller.entities.Profile;

import java.util.List;

public interface ContactService {
    Contact createContact(Profile owner, Profile contact);
    List<Profile> findOwnersContacts(String ownersID);
}
