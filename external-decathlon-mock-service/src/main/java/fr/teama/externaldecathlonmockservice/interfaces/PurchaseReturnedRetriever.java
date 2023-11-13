package fr.teama.externaldecathlonmockservice.interfaces;

import java.util.List;

public interface PurchaseReturnedRetriever {
    List<Long> getPurchasedReturned(List<Long> purchasedItems);

    void makePurchase(Long id);

    void returnPurchase(Long id);
}
