package edu.hm.crm.repository;

import edu.hm.crm.persistence.FormOfInteraction;
import edu.hm.crm.persistence.Interaction;
import edu.hm.crm.persistence.InteractionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for interaction repository.
 *
 * @author Simon Hirner
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InteractionRepositoryTest {

    private InteractionRepository interactionRepository;

    @Autowired
    public void setRepository(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    @Test
    public void createInteraction() {
        Interaction interaction = new Interaction(FormOfInteraction.EMAIL, "123456", LocalDateTime.of(2020, Month.JUNE, 12, 8,30));
        assertEquals(interaction, interactionRepository.save(interaction));
    }

    @Test
    public void findAllInteractions() {
        List<Interaction> interaction = interactionRepository.findAll();
        assertTrue(!interaction.isEmpty());
    }

}
