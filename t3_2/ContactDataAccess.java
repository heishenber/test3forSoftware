package com.software.t3_2;

import java.util.ArrayList;
import java.util.List;

class Contact {
    String name;
    String address;
    String phone;

    public Contact(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "姓名: " + name + ", 地址: " + address + ", 电话: " + phone;
    }
}

public class ContactDataAccess {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public boolean deleteContact(String name) {
        return contacts.removeIf(contact -> contact.name.equals(name));
    }
}