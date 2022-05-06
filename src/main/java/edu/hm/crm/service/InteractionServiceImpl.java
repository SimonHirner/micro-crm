package edu.hm.crm.service;

import edu.hm.crm.CRMApplication;
import edu.hm.crm.common.BadRequestException;
import edu.hm.crm.common.ResourceNotFoundException;
import edu.hm.crm.persistence.ContactRepository;
import edu.hm.crm.persistence.Interaction;
import edu.hm.crm.persistence.InteractionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Interaction service implementation.
 *
 * @author Simon Hirner
 */
@Service
public class InteractionServiceImpl implements InteractionService {

    private static final Logger logger = LoggerFactory.getLogger(CRMApplication.class);

    private final InteractionRepository interactionRepository;
    private final ContactRepository contactRepository;

    public InteractionServiceImpl(InteractionRepository interactionRepository, ContactRepository contactRepository) {
        this.interactionRepository = interactionRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Interaction> getAllInteractions() {
        logger.debug("Find all interactions");
        return interactionRepository.findAllByOrderByDateAndTimeDescIdDesc();
    }

    @Override
    public Optional<Interaction> getInteraction(String id) {
        logger.debug("Find interaction {}", id);
        return interactionRepository.findById(id);
    }

    @Override
    public Interaction saveInteraction(Interaction newInteraction) {
        if (newInteraction.getRelatedContactId() == null || newInteraction.getRelatedContactId().isEmpty()) {
            logger.debug("Save new interaction");
            return interactionRepository.save(newInteraction);
        }

        logger.debug("Verify contact ID");
        if (contactRepository.findById(newInteraction.getRelatedContactId()).isPresent()) {
            logger.debug("Save new interaction");
            return interactionRepository.save(newInteraction);
        } else {
            throw new BadRequestException("Contact ID is invalid");
        }
    }

    @Override
    public Interaction replaceInteraction(String id, Interaction newInteraction) {
        logger.debug("Replace interaction {}", id);
        return getInteraction(id)
                .map(interaction -> {
                    interaction.setNote(newInteraction.getNote());
                    interaction.setRelatedContactId(newInteraction.getRelatedContactId());
                    interaction.setDateAndTime(newInteraction.getDateAndTime());
                    interaction.setFormOfInteraction(newInteraction.getFormOfInteraction());
                    return saveInteraction(interaction);
                })
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public void deleteInteraction(String id) {
        logger.debug("Delete interaction {}", id);
        interactionRepository.deleteById(getInteraction(id).orElseThrow(() -> new ResourceNotFoundException(id)).getId());
    }

    @Override
    public void deleteAllInteractions() {
        logger.debug("Delete all interactions");
        interactionRepository.deleteAll();
    }

}
