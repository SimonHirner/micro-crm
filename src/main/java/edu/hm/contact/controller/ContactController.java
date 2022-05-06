package edu.hm.contact.controller;

import edu.hm.contact.ContactApplication;
import edu.hm.contact.common.BadRequestException;
import edu.hm.contact.common.ResourceNotFoundException;
import edu.hm.contact.common.SwaggerConfig;
import edu.hm.contact.persistence.Contact;
import edu.hm.contact.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Controller for contact operations.
 *
 * @author Simon Hirner
 */
@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "*")
@Api(tags = {SwaggerConfig.CONTACT_TAG})
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactApplication.class);

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    @ApiOperation(value = "Get all Contacts.")
    List<Contact> getAllContacts() {
        logger.debug("GET /contacts");
        return contactService.getAllContacts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Save new Contact.")
    Contact saveContact(@RequestBody Contact newContact) {
        logger.debug("POST /contacts");
        try {
            return contactService.saveContact(newContact);
        } catch (ConstraintViolationException exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Contact by ID.")
    Contact getContact(@PathVariable String id) {
        logger.debug("GET /contacts/{}", id);
        return contactService.getContact(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Replace Contact by ID with new Contact.")
    Contact replaceContact(@PathVariable String id, @RequestBody Contact newContact) {
        logger.debug("PUT /contacts/{}", id);
        return contactService.replaceContact(id, newContact);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Contact by ID.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteContact(@PathVariable String id) {
        logger.debug("DELETE /contacts/{}", id);
        contactService.deleteContact(id);
    }

}

