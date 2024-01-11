package fr.teama.affiliatedstoreservice.models.affiliatedstore;

import fr.teama.affiliatedstoreservice.models.CashBackAnnulationParameters;
import jakarta.persistence.*;

@Entity
@Table(name = "affiliated_store")
public class AffiliatedStore {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "cashback_rate")
    private float cashbackRate;

    private String siret;

    @Embedded
    @Column(name = "cashback_annulation_parameters")
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
                ", siret='" + siret + '\'' +
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

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public CashBackAnnulationParameters getCashBackAnnulationParameters() {
        return cashBackAnnulationParameters;
    }

    public void setCashBackAnnulationParameters(CashBackAnnulationParameters cashBackAnnulationParameters) {
        this.cashBackAnnulationParameters = cashBackAnnulationParameters;
    }
}
