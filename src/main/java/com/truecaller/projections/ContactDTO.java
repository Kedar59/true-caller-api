package com.truecaller.projections;

public class ContactDTO {
    private CallerID owner;
    private CallerID contact;

    public ContactDTO(){}
    public ContactDTO(CallerID owner, CallerID contact) {
        this.owner = owner;
        this.contact = contact;
    }

    public CallerID getOwner() {
        return owner;
    }

    public void setOwner(CallerID owner) {
        this.owner = owner;
    }

    public CallerID getContact() {
        return contact;
    }

    public void setContact(CallerID contact) {
        this.contact = contact;
    }
}
