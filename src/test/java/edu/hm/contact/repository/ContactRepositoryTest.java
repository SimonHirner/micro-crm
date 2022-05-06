package edu.hm.contact.repository;

import edu.hm.contact.persistence.Contact;
import edu.hm.contact.persistence.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for contact repository.
 *
 * @author Simon Hirner
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactRepositoryTest {

    private ContactRepository contactRepository;

    @Autowired
    public void setRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Test
    public void createContact() {
        Contact contact = new Contact("Vorname", "Nachname");
        assertEquals(contact, contactRepository.save(contact));
    }

    @Test
    public void findAllContacts() {
        List<Contact> contact = contactRepository.findAll();
        assertTrue(!contact.isEmpty());
    }

}
