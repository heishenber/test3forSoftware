package com.wpy.t3_3;

import com.wpy.t3_3.Contact;
import com.wpy.t3_3.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        return contactService.addContact(contact);
    }

    @DeleteMapping("/{name}")
    public String deleteContact(@PathVariable String name) {
        boolean removed = contactService.deleteContact(name);
        return removed ? "Contact deleted" : "Contact not found";
    }
}
