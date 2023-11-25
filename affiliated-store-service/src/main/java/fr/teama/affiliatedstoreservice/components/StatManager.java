package fr.teama.affiliatedstoreservice.components;

import fr.teama.affiliatedstoreservice.interfaces.IStatManager;
import fr.teama.affiliatedstoreservice.interfaces.proxy.ICashbackProxy;
import fr.teama.affiliatedstoreservice.models.AdminCashbackStatistic;
import fr.teama.affiliatedstoreservice.models.CashbackDTO;
import fr.teama.affiliatedstoreservice.models.CashbackStat;
import fr.teama.affiliatedstoreservice.repository.AffiliatedStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatManager implements IStatManager {

    private final AffiliatedStoreRepository affiliatedStoreRepository;

    private final ICashbackProxy cashbackProxy;

    @Autowired
    StatManager(AffiliatedStoreRepository affiliatedStoreRepository, ICashbackProxy cashbackProxy) {
        this.affiliatedStoreRepository = affiliatedStoreRepository;
        this.cashbackProxy = cashbackProxy;
    }

    @Override
    public AdminCashbackStatistic getCashbackStat() {
        List<CashbackDTO> cashbackDTOList = cashbackProxy.getCashbackTransactions();
        CashbackStat generalStat = new CashbackStat("Overall", 0, 0.0, 0.0);
        List<CashbackStat> statsByStore = new ArrayList<>();

        if (cashbackDTOList != null) {
            generalStat.setNumberOfCashbackTransactions(cashbackDTOList.size());
            generalStat.setAmountSpent(cashbackDTOList.stream().mapToDouble(CashbackDTO::getAmountSpent).sum());
            generalStat.setCashBackReturned(cashbackDTOList.stream().mapToDouble(CashbackDTO::getAmountReturned).sum());

            for (CashbackDTO cashbackDTO : cashbackDTOList) {
                String storeName = affiliatedStoreRepository.findBySiret(cashbackDTO.getSiret()).getName();

                statsByStore.stream().filter(s -> s.getName().equals(storeName)).findFirst().ifPresentOrElse(
                        s -> {
                            s.setNumberOfCashbackTransactions(s.getNumberOfCashbackTransactions() + 1);
                            s.setAmountSpent(s.getAmountSpent() + cashbackDTO.getAmountSpent());
                            s.setCashBackReturned(s.getCashBackReturned() + cashbackDTO.getAmountReturned());
                        },
                        () -> {
                            CashbackStat newStat = new CashbackStat(storeName, 1, cashbackDTO.getAmountSpent(), cashbackDTO.getAmountReturned());
                            statsByStore.add(newStat);
                        });

            }
        }

        return new AdminCashbackStatistic(generalStat, statsByStore);
    }
}
