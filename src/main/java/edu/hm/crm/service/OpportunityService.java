package edu.hm.crm.service;

import edu.hm.crm.persistence.Opportunity;

import java.util.List;
import java.util.Optional;

/**
 * Interface for opportunity service.
 *
 * @author Simon Hirner
 */
public interface OpportunityService {

    List<Opportunity> getAllOpportunities();

    Optional<Opportunity> getOpportunity(String id);

    Opportunity saveOpportunity(Opportunity newOpportunity);

    Opportunity replaceOpportunity(String id, Opportunity newOpportunity);

    void deleteOpportunity(String id);

    void deleteAllOpportunities();
}
