package fr.teama.externaldecathlonmockservice.components;

import fr.teama.externaldecathlonmockservice.helpers.LoggerHelper;
import fr.teama.externaldecathlonmockservice.interfaces.PurchaseReturnedRetriever;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseReturnedManager implements PurchaseReturnedRetriever {

    private List<String> purchaseReturnedList = new ArrayList<>();
    private List<String> purchaseMadeList = new ArrayList<>();

    @Override
    public List<String> getPurchasedReturned(List<String> purchasedItems) {
        List<String> purchaseReturned = new ArrayList<>();
        for(String id : purchasedItems) {
            LoggerHelper.logInfo("ID: " + id);
            if(purchaseMadeList.contains(id)) {
                LoggerHelper.logInfo("IN IF ");
                purchaseReturned.add(id);
            }
        }

        LoggerHelper.logInfo("LIST: " + purchaseReturned.toString());
        return purchaseReturned;
    }

    @Override
    public void makePurchase(String id) {
        purchaseMadeList.add(id);
    }

    @Override
    public void returnPurchase(String id) {
        purchaseReturnedList.add(id);
        purchaseMadeList.remove(id);
    }
}
