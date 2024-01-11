package fr.teama.affiliatedstoreservice.components;

import fr.teama.affiliatedstoreservice.interfaces.IStatManager;
import fr.teama.affiliatedstoreservice.models.AdminCashbackStatistic;
import fr.teama.affiliatedstoreservice.models.CashbackStat;
import fr.teama.affiliatedstoreservice.models.cashback.Cashback;
import fr.teama.affiliatedstoreservice.repository.affiliatedstore.AffiliatedStoreRepository;
import fr.teama.affiliatedstoreservice.repository.cashback.CashbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatManager implements IStatManager {

    private final AffiliatedStoreRepository affiliatedStoreRepository;
    private final CashbackRepository cashbackRepository;

    @Autowired
    StatManager(AffiliatedStoreRepository affiliatedStoreRepository, CashbackRepository cashbackRepository) {
        this.affiliatedStoreRepository = affiliatedStoreRepository;
        this.cashbackRepository = cashbackRepository;
    }

    @Override
    public AdminCashbackStatistic getCashbackStat() {
        List<Cashback> cashbackList = cashbackRepository.findAll();
        CashbackStat generalStat = new CashbackStat("Overall", 0, 0.0, 0.0);
        List<CashbackStat> statsByStore = new ArrayList<>();

        generalStat.setNumberOfCashbackTransactions(cashbackList.size());
        generalStat.setAmountSpent(cashbackList.stream().mapToDouble(Cashback::getAmountSpent).sum());
        generalStat.setCashBackReturned(cashbackList.stream().mapToDouble(Cashback::getAmountReturned).sum());

        for (Cashback cashback : cashbackList) {
            String storeName = affiliatedStoreRepository.findBySiret(cashback.getSiret()).getName();

            statsByStore.stream().filter(s -> s.getName().equals(storeName)).findFirst().ifPresentOrElse(
                    s -> {
                        s.setNumberOfCashbackTransactions(s.getNumberOfCashbackTransactions() + 1);
                        s.setAmountSpent(s.getAmountSpent() + cashback.getAmountSpent());
                        s.setCashBackReturned(s.getCashBackReturned() + cashback.getAmountReturned());
                    },
                    () -> {
                        CashbackStat newStat = new CashbackStat(storeName, 1, cashback.getAmountSpent(), cashback.getAmountReturned());
                        statsByStore.add(newStat);
                    });

        }

        return new AdminCashbackStatistic(generalStat, statsByStore);
    }
}
