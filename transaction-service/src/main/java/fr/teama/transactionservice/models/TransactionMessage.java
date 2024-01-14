package fr.teama.transactionservice.models;

import fr.teama.transactionservice.models.transaction.Transaction;

public class TransactionMessage {
    Transaction transaction;
    boolean republishing;

    public TransactionMessage() {
    }

    public TransactionMessage(Transaction transaction, boolean republishing) {
        this.transaction = transaction;
        this.republishing = republishing;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public boolean isRepublishing() {
        return republishing;
    }

    public void setRepublishing(boolean republishing) {
        this.republishing = republishing;
    }
}
