package fr.teama.cashbackservice.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class CashBackAnnulationParameters {
    private boolean cashBackAnnulationActivated;
    private String APIRouteForCashbackAnnulation;
    private ApiConfigurationMode specificAPIConfigurationMode;

    public CashBackAnnulationParameters(boolean cashBackAnnulationActivated, String APIRouteForCashbackAnnulation, ApiConfigurationMode specificAPIConfigurationMode) {
        this.cashBackAnnulationActivated = cashBackAnnulationActivated;
        this.APIRouteForCashbackAnnulation = APIRouteForCashbackAnnulation;
        this.specificAPIConfigurationMode = specificAPIConfigurationMode;
    }

    public CashBackAnnulationParameters() {
    }

    public boolean isCashBackAnnulationActivated() {
        return cashBackAnnulationActivated;
    }

    public void setCashBackAnnulationActivated(boolean cashBackAnnulationActivated) {
        this.cashBackAnnulationActivated = cashBackAnnulationActivated;
    }

    public String getAPIRouteForCashbackAnnulation() {
        return APIRouteForCashbackAnnulation;
    }

    public void setAPIRouteForCashbackAnnulation(String APIRouteForCashbackAnnulation) {
        this.APIRouteForCashbackAnnulation = APIRouteForCashbackAnnulation;
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
                ", APIRouteForCashbackAnnulation='" + APIRouteForCashbackAnnulation + '\'' +
                ", specificAPINumber='" + specificAPIConfigurationMode + '\'' +
                '}';
    }
}
