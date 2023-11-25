package fr.teama.affiliatedstoreservice.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class CashBackAnnulationParameters {
    private boolean cashBackAnnulationActivated;
    private String apiForCashbackAnnulation;
    private StoreAPIType specificStoreAPIType;

    public String getApiForCashbackAnnulation() {
        return apiForCashbackAnnulation;
    }

    public void setApiForCashbackAnnulation(String apiForCashbackAnnulation) {
        this.apiForCashbackAnnulation = apiForCashbackAnnulation;
    }

    public CashBackAnnulationParameters() {
    }

    public boolean isCashBackAnnulationActivated() {
        return cashBackAnnulationActivated;
    }

    public void setCashBackAnnulationActivated(boolean cashBackAnnulationActivated) {
        this.cashBackAnnulationActivated = cashBackAnnulationActivated;
    }


    public StoreAPIType getSpecificAPIConfigurationMode() {
        return specificStoreAPIType;
    }

    public void setSpecificAPIConfigurationMode(StoreAPIType specificStoreAPIType) {
        this.specificStoreAPIType = specificStoreAPIType;
    }

    @Override
    public String toString() {
        return "CashBackAnnulationParameters{" +
                "cashBackAnnulationActivated=" + cashBackAnnulationActivated +
                ", apiForCashbackAnnulation='" + apiForCashbackAnnulation + '\'' +
                ", specificAPIConfigurationMode=" + specificStoreAPIType +
                '}';
    }
}
