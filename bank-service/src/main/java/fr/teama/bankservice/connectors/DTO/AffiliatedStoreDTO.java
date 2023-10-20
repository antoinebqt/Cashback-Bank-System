package fr.teama.bankservice.connectors.DTO;

public class AffiliatedStoreDTO {
    private Long id;
    private String name;
    private Double cashbackRate;

    public AffiliatedStoreDTO() {
    }

    public AffiliatedStoreDTO(String name, Double cashbackRate) {
        this.name = name;
        this.cashbackRate = cashbackRate;
    }

    @Override
    public String toString() {
        return "AffiliatedStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cashbackRate=" + cashbackRate +
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
}
