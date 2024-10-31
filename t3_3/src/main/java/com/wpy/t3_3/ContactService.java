package com.wpy.t3_3;

import com.wpy.t3_3.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Contact addContact(Contact contact) {
        contacts.add(contact);
        return contact;
    }

    public boolean deleteContact(String name) {
        return contacts.removeIf(contact -> contact.getName().equals(name));
    }
}
