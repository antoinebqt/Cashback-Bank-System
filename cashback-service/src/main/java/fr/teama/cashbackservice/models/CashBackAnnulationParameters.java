package fr.teama.cashbackservice.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class CashBackAnnulationParameters {
    private boolean cashBackAnnulationActivated;
    private String apiForCashbackAnnulation;
    private ApiConfigurationMode specificAPIConfigurationMode;

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


    public ApiConfigurationMode getSpecificAPIConfigurationMode() {
        return specificAPIConfigurationMode;
    }

    public void setSpecificAPIConfigurationMode(ApiConfigurationMode specificAPIConfigurationMode) {
        this.specificAPIConfigurationMode = specificAPIConfigurationMode;
    }

    @Override
    public String toString() {
        return "CashBackAnnulationParameters{" +
                "cashBackAnnulationActivated=" + cashBackAnnulationActivated +
                ", apiForCashbackAnnulation='" + apiForCashbackAnnulation + '\'' +
                ", specificAPIConfigurationMode=" + specificAPIConfigurationMode +
                '}';
    }
}
