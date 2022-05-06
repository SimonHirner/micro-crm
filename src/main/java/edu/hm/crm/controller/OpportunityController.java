package edu.hm.crm.controller;

import edu.hm.crm.CRMApplication;
import edu.hm.crm.common.BadRequestException;
import edu.hm.crm.common.ResourceNotFoundException;
import edu.hm.crm.common.SwaggerConfig;
import edu.hm.crm.persistence.Opportunity;
import edu.hm.crm.service.OpportunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Controller for opportunity operations.
 *
 * @author Simon Hirner
 */
@RestController
@RequestMapping("/opportunities")
@CrossOrigin(origins = "*")
@Api(tags = {SwaggerConfig.OPPORTUNITY_TAG})
public class OpportunityController {

    private static final Logger logger = LoggerFactory.getLogger(CRMApplication.class);

    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @GetMapping
    @ApiOperation(value = "Get all Opportunities.")
    List<Opportunity> getAllOpportunities() {
        logger.debug("GET /opportunities");
        return opportunityService.getAllOpportunities();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Save new Opportunity.")
    Opportunity saveOpportunity(@RequestBody Opportunity newOpportunity) {
        logger.debug("POST /opportunities");
        try {
            return opportunityService.saveOpportunity(newOpportunity);
        } catch (ConstraintViolationException exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Opportunity by ID.")
    Opportunity getOpportunity(@PathVariable String id) {
        logger.debug("GET /opportunities/{}", id);
        return opportunityService.getOpportunity(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Replace Opportunity by ID with new Opportunity.")
    Opportunity replaceOpportunity(@PathVariable String id, @RequestBody Opportunity newOpportunity) {
        logger.debug("PUT /opportunities/{}", id);
        return opportunityService.replaceOpportunity(id, newOpportunity);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Opportunity by ID.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOpportunity(@PathVariable String id) {
        logger.debug("DELETE /opportunities/{}", id);
        opportunityService.deleteOpportunity(id);
    }

}

