package com.truecaller.services.impls;

import com.truecaller.controllers.ProfileController;
import com.truecaller.entities.Contact;
import com.truecaller.entities.Profile;
import com.truecaller.repositories.ContactRepository;
import com.truecaller.services.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImplements implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    private Logger logger = LoggerFactory.getLogger(ProfileController.class);
    @Override
    public Contact createContact(Profile owner, Profile contact) {
        Contact newContact = new Contact(owner,contact);
        return contactRepository.save(newContact);
    }

    @Override
    public List<Profile> findOwnersContacts(String ownersID) {
        return contactRepository.findByOwner_Id(ownersID)
                .stream()
                .map(Contact::getContact)
                .collect(Collectors.toList());
    }
}
