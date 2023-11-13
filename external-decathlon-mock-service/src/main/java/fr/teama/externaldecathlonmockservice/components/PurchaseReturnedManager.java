package fr.teama.externaldecathlonmockservice.components;

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
        for(Long id : purchasedItems) {
            if(purchaseReturnedList.contains(id)) {
                purchaseReturned.add(id);
            }
        }
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
