package fr.teama.externalcarrefourmockservice.components;

import fr.teama.externalcarrefourmockservice.interfaces.PurchaseReturnedRetriever;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseReturnedManager implements PurchaseReturnedRetriever {

    private List<String> purchaseReturnedList = new ArrayList<>();
    private List<String> purchaseMadeList = new ArrayList<>();

    @Override
    public List<String> getPurchasedReturned() {
        return purchaseReturnedList;
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
