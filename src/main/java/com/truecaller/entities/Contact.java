package com.truecaller.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "Contact")
@CompoundIndexes({
        @CompoundIndex(name = "owner_contact_unique_idx", def = "{'owner': 1, 'contact': 1}", unique = true)
})
public class Contact {

    @Id
    private String id;

    @DBRef
    private Profile owner;

    @DBRef
    private Profile contact;

    public Contact() {
    }

    public Contact(Profile owner, Profile contact) {
        this.owner = owner;
        this.contact = contact;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    public Profile getContact() {
        return contact;
    }

    public void setContact(Profile contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", owner=" + owner +
                ", contact=" + contact +
                '}';
    }
}
