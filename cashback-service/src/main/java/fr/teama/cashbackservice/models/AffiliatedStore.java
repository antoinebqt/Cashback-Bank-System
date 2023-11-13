package fr.teama.cashbackservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AffiliatedStore {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private float cashbackRate;

    private String siret;

    public AffiliatedStore() {
    }

    public AffiliatedStore(String name, float cashbackRate) {
        this.name = name;
        this.cashbackRate = cashbackRate;
    }

    @Override
    public String toString() {
        return "AffiliatedStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cashbackRate=" + cashbackRate +
                ", siret='" + siret + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
