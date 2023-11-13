package fr.teama.externalcarrefourmockservice.interfaces;

import java.util.List;

public interface PurchaseReturnedRetriever {
    public List<Long> getPurchasedReturned();

    public void makePurchase(Long id);

    public void returnPurchase(Long id);
}
