package fr.teama.affiliatedstoreservice.models;

import java.util.List;

public class AdminCashbackStatistic {
    private CashbackStat generalStat;

    private List<CashbackStat> statsByStore;

    public AdminCashbackStatistic(CashbackStat generalStat, List<CashbackStat> statsByStore) {
        this.generalStat = generalStat;
        this.statsByStore = statsByStore;
    }

    public CashbackStat getGeneralStat() {
        return generalStat;
    }

    public void setGeneralStat(CashbackStat generalStat) {
        this.generalStat = generalStat;
    }

    public List<CashbackStat> getStatsByStore() {
        return statsByStore;
    }

    public void setStatsByStore(List<CashbackStat> statsByStore) {
        this.statsByStore = statsByStore;
    }

    @Override
    public String toString() {
        return "AdminCashbackStatistic{" +
                "generalStat=" + generalStat +
                ", statsByStore=" + statsByStore +
                '}';
    }
}
