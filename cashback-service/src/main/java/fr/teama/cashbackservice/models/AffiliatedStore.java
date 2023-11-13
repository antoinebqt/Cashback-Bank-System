package fr.teama.cashbackservice.models;

import jakarta.persistence.*;

@Entity
public class AffiliatedStore {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private float cashbackRate;

    @Embedded
    CashBackAnnulationParameters cashBackAnnulationParameters;

    public AffiliatedStore() {
    }
    public AffiliatedStore(String name, float cashbackRate) {
        this.name = name;
        this.cashbackRate = cashbackRate;
    }

    public AffiliatedStore(String name, float cashbackRate,CashBackAnnulationParameters cashBackAnnulationParameters) {
        this.name = name;
        this.cashbackRate = cashbackRate;
        this.cashBackAnnulationParameters = cashBackAnnulationParameters;
    }

    @Override
    public String toString() {
        return "AffiliatedStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cashbackRate=" + cashbackRate +
                ", cashBackAnnulationParameters=" + cashBackAnnulationParameters +
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

    public CashBackAnnulationParameters getCashBackAnnulationParameters() {
        return cashBackAnnulationParameters;
    }

    public void setCashBackAnnulationParameters(CashBackAnnulationParameters cashBackAnnulationParameters) {
        this.cashBackAnnulationParameters = cashBackAnnulationParameters;
    }
}
