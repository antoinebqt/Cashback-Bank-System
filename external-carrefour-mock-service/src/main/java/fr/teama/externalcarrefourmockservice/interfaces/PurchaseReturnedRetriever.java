package fr.teama.externalcarrefourmockservice.interfaces;

import java.util.List;

public interface PurchaseReturnedRetriever {
    List<Long> getPurchasedReturned();

    void makePurchase(Long id);

    void returnPurchase(Long id);
}
