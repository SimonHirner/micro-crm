package edu.hm.crm;

import edu.hm.crm.persistence.*;
import edu.hm.crm.service.ContactService;
import edu.hm.crm.service.InteractionService;
import edu.hm.crm.service.OpportunityService;
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
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Contact microservice application.
 *
 * @author Simon Hirner
 */
@SpringBootApplication
public class CRMApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CRMApplication.class);

    private final ContactService contactService;
    private final InteractionService interactionService;
    private final OpportunityService opportunityService;

    public CRMApplication(ContactService contactService, InteractionService interactionService, OpportunityService opportunityService) {
        this.contactService = contactService;
        this.interactionService = interactionService;
        this.opportunityService = opportunityService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CRMApplication.class, args);
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

            interactionService.deleteAllInteractions();

            Interaction interaction1 = new Interaction(FormOfInteraction.EMAIL, "","", LocalDateTime.of(2021, Month.FEBRUARY, 2, 12,30));
            Interaction interaction2 = new Interaction(FormOfInteraction.MEETING, "Produktberatung","",LocalDateTime.of(2019, Month.MAY, 13, 16,45));
            Interaction interaction3 = new Interaction(FormOfInteraction.PHONE, "Bittet um einen Rückruf","",LocalDateTime.of(2020, Month.NOVEMBER, 22, 8,30));

            interactionService.saveInteraction(interaction1);
            interactionService.saveInteraction(interaction2);
            interactionService.saveInteraction(interaction3);

            opportunityService.deleteAllOpportunities();

            Opportunity opportunity1 = new Opportunity(LocalDate.of(2023, Month.JANUARY, 6),
                    1800.0, 1800.0, 100.0, Status.PLANNED, "Hohes Kaufinteresse geäußert",
                    "");
            Opportunity opportunity2 = new Opportunity(LocalDate.of(2022, Month.DECEMBER, 23),
                    12000.0, 13000.0, 800.0, Status.IN_PROGRESS, "",
                    "");

            opportunityService.saveOpportunity(opportunity1);
            opportunityService.saveOpportunity(opportunity2);
        } catch (ConstraintViolationException exception) {
            logger.error(exception.getMessage());
        }
    }

}
