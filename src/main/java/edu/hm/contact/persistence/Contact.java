package edu.hm.contact.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Data model for contacts.
 *
 * @author Simon Hirner
 */
@Document(collection = "contacts")
public class Contact {

    @Id
    private String id;

    @NotBlank(message = "First name should not be blank")
    private String firstName;

    @NotBlank(message = "Last name should not be blank")
    private String lastName;

    private Gender gender;

    @Past(message = "Date of birth should be in the past")
    private LocalDate dateOfBirth;

    @Email(message = "Email should be valid")
    @Indexed(unique = true)
    private String email;

    @Pattern(regexp = "^\\+\\d{6,14}$", message = "Phone number should be valid")
    private String phoneNumber;

    private Address address;

    /**
     * Default constructor.
     */
    private Contact() {
        // Is needed
    }

    /**
     * Constructor.
     *
     * @param firstName first name
     * @param lastName last name
     */
    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructor.
     *
     * @param firstName first name
     * @param lastName last name
     * @param gender gender
     * @param dateOfBirth date of birth
     * @param email email address
     * @param phoneNumber phone number
     * @param address address
     */
    public Contact(String firstName, String lastName,
                   Gender gender, LocalDate dateOfBirth,
                   String email, String phoneNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "id='" + id + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + '}';
    }

}

