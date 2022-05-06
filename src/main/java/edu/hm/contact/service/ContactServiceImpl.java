package edu.hm.contact.service;

import edu.hm.contact.ContactApplication;
import edu.hm.contact.common.ResourceNotFoundException;
import edu.hm.contact.persistence.Contact;
import edu.hm.contact.persistence.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Contact service implementation.
 *
 * @author Simon Hirner
 */
@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactApplication.class);

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAllContacts() {
        logger.debug("Find all contacts");
        return contactRepository.findAllByOrderByLastNameAscIdAsc();
    }

    @Override
    public Optional<Contact> getContact(String id) {
        logger.debug("Find contact {}", id);
        return contactRepository.findById(id);
    }

    @Override
    public Contact saveContact(Contact newContact) {
        logger.debug("Save new contact");
        return contactRepository.save(newContact);
    }

    @Override
    public Contact replaceContact(String id, Contact newContact) {
        logger.debug("Replace contact {}", id);
        return getContact(id)
                .map(contact -> {
                    contact.setFirstName(newContact.getFirstName());
                    contact.setLastName(newContact.getLastName());
                    contact.setGender(newContact.getGender());
                    contact.setEmail(newContact.getEmail());
                    contact.setDateOfBirth(newContact.getDateOfBirth());
                    contact.setPhoneNumber(newContact.getPhoneNumber());
                    contact.setAddress(newContact.getAddress());
                    return saveContact(contact);
                })
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public void deleteContact(String id) {
        logger.debug("Delete contact {}", id);
        contactRepository.deleteById(getContact(id).orElseThrow(() -> new ResourceNotFoundException(id)).getId());
    }

    @Override
    public void deleteAllContacts() {
        logger.debug("Delete all contacts");
        contactRepository.deleteAll();
    }

}
