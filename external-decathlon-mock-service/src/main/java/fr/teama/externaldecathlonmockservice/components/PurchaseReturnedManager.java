package fr.teama.externaldecathlonmockservice.components;

import fr.teama.externaldecathlonmockservice.helpers.LoggerHelper;
import fr.teama.externaldecathlonmockservice.interfaces.PurchaseReturnedRetriever;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseReturnedManager implements PurchaseReturnedRetriever {

    private List<Long> purchaseReturnedList = new ArrayList<>();
    private List<Long> purchaseMadeList = new ArrayList<>();

    @Override
    public List<Long> getPurchasedReturned(List<Long> purchasedItems) {
        List<Long> purchaseReturned = new ArrayList<>();
        LoggerHelper.logInfo("ID: " + purchaseReturnedList.get(0));
        LoggerHelper.logInfo("ID: " + purchaseReturnedList.get(1));
        LoggerHelper.logInfo("ID: " + purchaseReturnedList.get(2));
        LoggerHelper.logInfo("ID: " + purchaseReturnedList.get(3));
        LoggerHelper.logInfo("ID: " + purchaseReturnedList.get(4));
        for(Long id : purchasedItems) {
            LoggerHelper.logInfo("ID: " + id);
            if(purchaseReturnedList.contains(id)) {
                LoggerHelper.logInfo("IN IF ");
                purchaseReturned.add(id);
            }
        }

        LoggerHelper.logInfo("LIST: " + purchaseReturned.toString());
        return purchaseReturned;
    }

    @Override
    public void makePurchase(Long id) {
        purchaseMadeList.add(id);
    }

    @Override
    public void returnPurchase(Long id) {
        purchaseReturnedList.add(id);
        purchaseMadeList.remove(id);
    }
}
