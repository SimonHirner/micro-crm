package edu.hm.contact;

import edu.hm.contact.persistence.Address;
import edu.hm.contact.persistence.Contact;
import edu.hm.contact.persistence.Country;
import edu.hm.contact.persistence.Gender;
import edu.hm.contact.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;

/**
 * Contact microservice application.
 *
 * @author Simon Hirner
 */
@SpringBootApplication
public class ContactApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ContactApplication.class);

    private final ContactService contactService;

    public ContactApplication(ContactService contactService) {
        this.contactService = contactService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ContactApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String mongodbHost = System.getenv().getOrDefault("MONGODB_HOST", "localhost");
        logger.info("Environment variable MONGODB_HOST=" + mongodbHost);
        initializeDatabase();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    private void initializeDatabase() {
        try {
            logger.info("Initialize database");

            contactService.deleteAllContacts();

            Address address1 = new Address("Eichenstraße 11", "Apartment A",
                    "80331", "München", Country.GERMANY);
            Address address2 = new Address("Lindenstraße 21", "",
                    "6020", "Innsbruck", Country.AUSTRIA);
            Contact contact1 = new Contact("Max", "Mustermann", Gender.MALE,
                    LocalDate.of(1999, Month.JULY, 3), "max@mustermann.de",
                    "+49012940323", address1);
            Contact contact2 = new Contact("Erika", "Mustermann", Gender.FEMALE,
                    LocalDate.of(1983, Month.DECEMBER, 23), "erika@mustermann.de",
                    "+49042946782", address2);

            contactService.saveContact(contact1);
            contactService.saveContact(contact2);
        } catch (ConstraintViolationException exception) {
            logger.error(exception.getMessage());
        }
    }

}
