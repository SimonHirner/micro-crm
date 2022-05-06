package edu.hm.crm.repository;

import edu.hm.crm.persistence.Opportunity;
import edu.hm.crm.persistence.OpportunityRepository;
import edu.hm.crm.persistence.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for opportunity repository.
 *
 * @author Simon Hirner
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OpportunityRepositoryTest {

    private OpportunityRepository opportunityRepository;

    @Autowired
    public void setRepository(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    @Test
    public void createOpportunity() {
        Opportunity opportunity = new Opportunity(LocalDate.of(2022, Month.JANUARY, 1),
                Status.PLANNED, "");
        assertEquals(opportunity, opportunityRepository.save(opportunity));
    }

    @Test
    public void findAllOpportunities() {
        List<Opportunity> opportunity = opportunityRepository.findAll();
        assertTrue(!opportunity.isEmpty());
    }

}
