package fr.teama.transactionservice.connectors;

import fr.teama.transactionservice.exceptions.BankAccountUnavailableException;
import fr.teama.transactionservice.helpers.LoggerHelper;
import fr.teama.transactionservice.interfaces.IAccountProxy;
import fr.teama.transactionservice.models.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AccountProxy implements IAccountProxy {
    @Value("${account.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Long getBankAccountIdByCard(Card card) throws BankAccountUnavailableException {
        try {
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/account/account-by-card", card, Long.class).getBody();
        } catch (Exception e) {
            LoggerHelper.logError("Cannot get bank account id by card because BankAccount service is unavailable");
            throw new BankAccountUnavailableException();
        }
    }

    @Override
    public boolean checkBalance(Long bankAccountId, double amount) throws BankAccountUnavailableException {
        try {
            return Boolean.TRUE.equals(restTemplate.postForEntity(apiBaseUrlHostAndPort + "/balance/check/" + bankAccountId, amount, Boolean.class).getBody());
        } catch (Exception e) {
            LoggerHelper.logError("Cannot check balance because BankAccount service is unavailable");
            throw new BankAccountUnavailableException();
        }
    }
}
