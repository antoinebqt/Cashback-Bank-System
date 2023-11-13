package fr.teama.externalcarrefourmockservice.interfaces;

import java.util.List;

public interface PurchaseReturnedRetriever {
    List<String> getPurchasedReturned();

    void makePurchase(String id);

    void returnPurchase(String id);
}
