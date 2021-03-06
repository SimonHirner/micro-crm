package edu.hm.crm.service;

import edu.hm.crm.persistence.Contact;

import java.util.List;
import java.util.Optional;

/**
 * Interface for contact service.
 *
 * @author Simon Hirner
 */
public interface ContactService {

    List<Contact> getAllContacts();

    Optional<Contact> getContact(String id);

    Contact saveContact(Contact newContact);

    Contact replaceContact(String id, Contact newContact);

    void deleteContact(String id);

    void deleteAllContacts();
}
