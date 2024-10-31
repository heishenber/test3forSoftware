package com.software.t3_2;

import java.util.List;

public class ContactService {
    private ContactDataAccess dataAccess = new ContactDataAccess();

    public String addContact(String name, String address, String phone) {
        Contact contact = new Contact(name, address, phone);
        dataAccess.addContact(contact);
        return "联系人已添加。";
    }

    public String listContacts() {
        List<Contact> contacts = dataAccess.getAllContacts();
        StringBuilder result = new StringBuilder();
        for (Contact contact : contacts) {
            result.append(contact.toString()).append("\n");
        }
        return result.toString();
    }

    public String deleteContact(String name) {
        if (dataAccess.deleteContact(name)) {
            return "联系人已删除。";
        } else {
            return "联系人未找到。";
        }
    }
}