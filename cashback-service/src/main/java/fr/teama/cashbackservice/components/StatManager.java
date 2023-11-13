package fr.teama.cashbackservice.components;

import fr.teama.cashbackservice.connectors.externalDTO.TransactionDTO;
import fr.teama.cashbackservice.interfaces.IStatManager;
import fr.teama.cashbackservice.interfaces.proxy.IBankProxy;
import fr.teama.cashbackservice.models.AdminCashbackStatistic;
import fr.teama.cashbackservice.models.CashbackStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatManager implements IStatManager {

    @Autowired
    private IBankProxy bankProxy;

    @Override
    public AdminCashbackStatistic getCashback() {
        List<TransactionDTO> transactionDTOList = bankProxy.getCashbackTransactions();
        CashbackStat generalStat = new CashbackStat("Overall", 0, 0.0, 0.0);
        List<CashbackStat> statsByStore = new ArrayList<>();

        if (transactionDTOList != null) {
            generalStat.setNumberOfTransactions(transactionDTOList.size());
            generalStat.setAmountSpent(transactionDTOList.stream().mapToDouble(t -> t.getPayment().getAmount()).sum());
            generalStat.setCashBackReturned(transactionDTOList.stream().mapToDouble(TransactionDTO::getCashbackReturned).sum());

            for (TransactionDTO transactionDTO : transactionDTOList) {
                boolean storeFound = false;
                for (CashbackStat stat : statsByStore) {
                    if (stat.getSiret().equals(transactionDTO.getPayment().getSiret())) {
                        stat.setNumberOfTransactions(stat.getNumberOfTransactions() + 1);
                        stat.setAmountSpent(stat.getAmountSpent() + transactionDTO.getPayment().getAmount());
                        stat.setCashBackReturned(stat.getCashBackReturned() + transactionDTO.getCashbackReturned());
                        storeFound = true;
                        break;
                    }
                }
                if (!storeFound) {
                    CashbackStat newStat = new CashbackStat(transactionDTO.getPayment().getSiret(), 1, transactionDTO.getPayment().getAmount(), transactionDTO.getCashbackReturned());
                    statsByStore.add(newStat);
                }
            }
        }

        return new AdminCashbackStatistic(generalStat, statsByStore);
    }
}
