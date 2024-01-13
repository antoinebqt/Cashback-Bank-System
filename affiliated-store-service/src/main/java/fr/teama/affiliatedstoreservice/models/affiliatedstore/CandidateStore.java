package fr.teama.affiliatedstoreservice.models.affiliatedstore;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate_store")
public class CandidateStore {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String email;
    @Column(name = "phone_number")
    private int phoneNumber;
    private String address;
    private String city;
    @Column(name = "postal_code")
    private int postalCode;
    private String country;
    private String website;
    private String description;
    @Column(name = "cashback_rate")
    private float cashbackRate;

    private String siret;

    private boolean accepted;

    private boolean refused;

    private String reason;

    public CandidateStore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCashbackRate() {
        return cashbackRate;
    }

    public void setCashbackRate(float cashbackRate) {
        this.cashbackRate = cashbackRate;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        accepted = accepted;
    }

    public boolean isRefused() {
        return refused;
    }

    public void setRefused(boolean refused) {
        refused = refused;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
