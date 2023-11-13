package fr.teama.externaldecathlonmockservice.interfaces;

import java.util.List;

public interface PurchaseReturnedRetriever {
    List<String> getPurchasedReturned(List<String> purchasedItems);

    void makePurchase(String id);

    void returnPurchase(String id);
}
