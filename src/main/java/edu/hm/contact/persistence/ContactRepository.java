package edu.hm.contact.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository for contacts.
 *
 * @author Simon Hirner
 */
public interface ContactRepository extends MongoRepository<Contact, String> {
    List<Contact> findAllByOrderByLastNameAscIdAsc();
}
