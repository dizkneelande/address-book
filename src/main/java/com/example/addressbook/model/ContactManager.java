package com.example.addressbook.model;

import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private static IContactDAO contactDAO;
    public ContactManager(IContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public List<Contact> searchContacts(String query) {
        return contactDAO.getAllContacts()
                .stream()
                .filter(contact -> isContactMatched(contact, query))
                .toList();
    }

    private boolean isContactMatched(Contact contact, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = contact.getFullName()
                + " " + contact.getEmail()
                + " " + contact.getPhone();
        return searchString.toLowerCase().contains(query);
    }


    public static void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    public static void deleteContact(Contact contact) {
        contactDAO.deleteContact(contact);
    }

    public static void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
    }

    public static List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }
}