package fr.teama.accountservice.connectors.externalDTO;

public class AffiliatedStoreDTO {
    private Long id;
    private String name;
    private Double cashbackRate;
    private String siret;

    public AffiliatedStoreDTO() {
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

    public Double getCashbackRate() {
        return cashbackRate;
    }

    public void setCashbackRate(Double cashbackRate) {
        this.cashbackRate = cashbackRate;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }
}
