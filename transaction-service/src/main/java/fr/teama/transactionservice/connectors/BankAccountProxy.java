package fr.teama.transactionservice.connectors;

import fr.teama.transactionservice.exceptions.BankAccountUnavailableException;
import fr.teama.transactionservice.helpers.LoggerHelper;
import fr.teama.transactionservice.interfaces.IBankAccountProxy;
import fr.teama.transactionservice.models.Card;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BankAccountProxy implements IBankAccountProxy {
    @Value("${bank.account.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Long getBankAccountIdByCard(Card card) throws BankAccountUnavailableException {
        try {
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/account", card, Long.class).getBody();
        } catch (Exception e) {
            LoggerHelper.logError("Cannot get bank account id by card because BankAccount service is unavailable");
            throw new BankAccountUnavailableException();
        }
    }

    @Override
    public boolean checkBalance(Long bankAccountId, double amount) throws BankAccountUnavailableException {
        try {
            return Boolean.TRUE.equals(restTemplate.postForEntity(apiBaseUrlHostAndPort + "/account/" + bankAccountId, amount, Boolean.class).getBody());
        } catch (Exception e) {
            LoggerHelper.logError("Cannot check balance because BankAccount service is unavailable");
            throw new BankAccountUnavailableException();
        }
    }
}
