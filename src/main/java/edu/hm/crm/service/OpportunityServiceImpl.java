package edu.hm.crm.service;

import edu.hm.crm.CRMApplication;
import edu.hm.crm.common.BadRequestException;
import edu.hm.crm.common.ResourceNotFoundException;
import edu.hm.crm.persistence.ContactRepository;
import edu.hm.crm.persistence.Opportunity;
import edu.hm.crm.persistence.OpportunityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Opportunity service implementation.
 *
 * @author Simon Hirner
 */
@Service
public class OpportunityServiceImpl implements OpportunityService {

    private static final Logger logger = LoggerFactory.getLogger(CRMApplication.class);

    private final OpportunityRepository opportunityRepository;
    private final ContactRepository contactRepository;

    public OpportunityServiceImpl(OpportunityRepository opportunityRepository, ContactRepository contactRepository) {
        this.opportunityRepository = opportunityRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Opportunity> getAllOpportunities() {
        logger.debug("Find all opportunities");
        return opportunityRepository.findAllByOrderByEstimatedCloseDateAscIdAsc();
    }

    @Override
    public Optional<Opportunity> getOpportunity(String id) {
        logger.debug("Find opportunity {}", id);
        return opportunityRepository.findById(id);
    }

    @Override
    public Opportunity saveOpportunity(Opportunity newOpportunity) {
        if (newOpportunity.getRelatedContactId() == null || newOpportunity.getRelatedContactId().isEmpty()) {
            logger.debug("Save new interaction");
            return opportunityRepository.save(newOpportunity);
        }

        logger.debug("Verify contact ID");
        logger.debug("Save new opportunity");
        return opportunityRepository.save(newOpportunity);

//        if (contactRepository.findById(newOpportunity.getRelatedContactId()).isPresent()) {
//            logger.debug("Save new opportunity");
//            return opportunityRepository.save(newOpportunity);
//        } else {
//            throw new BadRequestException("Contact ID is invalid");
//        }
    }

    @Override
    public Opportunity replaceOpportunity(String id, Opportunity newOpportunity) {
        logger.debug("Replace opportunity {}", id);
        return getOpportunity(id)
                .map(opportunity -> {
                    opportunity.setBudget(newOpportunity.getBudget());
                    opportunity.setDiscount(newOpportunity.getDiscount());
                    opportunity.setEstimatedCloseDate(newOpportunity.getEstimatedCloseDate());
                    opportunity.setNote(newOpportunity.getNote());
                    opportunity.setStatus(newOpportunity.getStatus());
                    opportunity.setValue(newOpportunity.getValue());
                    opportunity.setRelatedContactId(newOpportunity.getRelatedContactId());
                    return saveOpportunity(opportunity);
                })
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public void deleteOpportunity(String id) {
        logger.debug("Delete opportunity {}", id);
        opportunityRepository.deleteById(getOpportunity(id).orElseThrow(() -> new ResourceNotFoundException(id)).getId());
    }

    @Override
    public void deleteAllOpportunities() {
        logger.debug("Delete all opportunities");
        opportunityRepository.deleteAll();
    }

}
