package fr.teama.cashbackservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AffiliatedStore {

    private String name;
    @Id
    private Long id;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public AffiliatedStore() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
