package edu.hm.crm.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

/**
 * Data model for interactions.
 *
 * @author Simon Hirner
 */
@Document(collection = "interactions")
public class Interaction {

    @Id
    private String id;

    private FormOfInteraction formOfInteraction;

    private String note;

    private String relatedContactId;

    @Past(message = "Date and time should be in the past")
    private LocalDateTime dateAndTime;

    /**
     * Default constructor.
     */
    private Interaction() {
        // Is needed
    }

    /**
     * Constructor.
     *
     * @param formOfInteraction form of interaction
     * @param relatedContactId related contact ID
     * @param dateAndTime date and time of interaction
     */
    public Interaction(FormOfInteraction formOfInteraction, String relatedContactId,
                       LocalDateTime dateAndTime) {
        this.formOfInteraction = formOfInteraction;
        this.relatedContactId = relatedContactId;
        this.dateAndTime = dateAndTime;
    }

    /**
     * Constructor.
     *
     * @param formOfInteraction form of interaction
     * @param note note
     * @param relatedContactId related contact ID
     * @param dateAndTime date and time of interaction
     */
    public Interaction(FormOfInteraction formOfInteraction, String note,
                       String relatedContactId, LocalDateTime dateAndTime) {
        this.formOfInteraction = formOfInteraction;
        this.note = note;
        this.relatedContactId = relatedContactId;
        this.dateAndTime = dateAndTime;
    }

    public String getId() {
        return id;
    }

    public FormOfInteraction getFormOfInteraction() {
        return formOfInteraction;
    }

    public void setFormOfInteraction(FormOfInteraction formOfInteraction) {
        this.formOfInteraction = formOfInteraction;
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

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "Interaction{"
                + "id='" + id + '\''
                + ", formOfInteraction=" + formOfInteraction
                + ", dateAndTime=" + dateAndTime
                + '}';
    }
}
