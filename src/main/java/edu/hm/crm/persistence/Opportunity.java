package edu.hm.crm.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

/**
 * Data model for opportunities.
 *
 * @author Simon Hirner
 */
@Document(collection = "opportunities")
public class Opportunity {

    @Id
    private String id;

    private LocalDate estimatedCloseDate;

    private double value;

    private double budget;

    private double discount;

    private Status status;

    private String note;

    private String relatedContactId;

    /**
     * Default constructor.
     */
    private Opportunity() {
        // Is needed
    }

    /**
     * Constructor
     *
     * @param estimatedCloseDate estimated close date
     * @param status status
     */
    public Opportunity(LocalDate estimatedCloseDate, Status status, String relatedContactId) {
        this.estimatedCloseDate = estimatedCloseDate;
        this.status = status;
        this.relatedContactId = relatedContactId;
    }

    /**
     * Constructor.
     *
     * @param estimatedCloseDate estimated close date
     * @param value value amount
     * @param budget budget amount
     * @param discount discount amount
     * @param status status
     * @param note note
     * @param relatedContactId related contact ID
     */
    public Opportunity(LocalDate estimatedCloseDate, double value, double budget, double discount,
                       Status status, String note, String relatedContactId) {
        this.estimatedCloseDate = estimatedCloseDate;
        this.value = value;
        this.budget = budget;
        this.discount = discount;
        this.status = status;
        this.note = note;
        this.relatedContactId = relatedContactId;
    }

    public String getId() {
        return id;
    }

    public LocalDate getEstimatedCloseDate() {
        return estimatedCloseDate;
    }

    public void setEstimatedCloseDate(LocalDate estimatedCloseDate) {
        this.estimatedCloseDate = estimatedCloseDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRelatedContactId() {
        return relatedContactId;
    }

    public void setRelatedContactId(String relatedContactId) {
        this.relatedContactId = relatedContactId;
    }

    @Override
    public String toString() {
        return "Opportunity{"
                + "id='" + id + '\''
                + ", status='" + status + '\''
                + '}';
    }

}
