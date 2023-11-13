package fr.teama.externalcarrefourmockservice.components;

import fr.teama.externalcarrefourmockservice.interfaces.PurchaseReturnedRetriever;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseReturnedManager implements PurchaseReturnedRetriever {

    private List<Long> purchaseReturnedList = new ArrayList<>();
    private List<Long> purchaseMadeList = new ArrayList<>();

    @Override
    public List<Long> getPurchasedReturned() {
        return purchaseReturnedList;
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
