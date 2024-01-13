package fr.teama.transactionservice.exceptions;

public class BankAccountNotFoundException extends Exception {

        String field;

        String iban;

        public BankAccountNotFoundException(String field, String iban) {
                this.field = field;
                this.iban = iban;
        }

        public BankAccountNotFoundException() {
        }

        public String getIban() {
                return iban;
        }

        public String getField() {
                return field;
        }
}
