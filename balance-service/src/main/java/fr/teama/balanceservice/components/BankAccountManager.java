package fr.teama.balanceservice.components;

import fr.teama.balanceservice.controllers.dto.CardDTO;
import fr.teama.balanceservice.exceptions.BankAccountNotFoundException;
import fr.teama.balanceservice.exceptions.InvalidAccountPasswordException;
import fr.teama.balanceservice.exceptions.InvalidCardException;
import fr.teama.balanceservice.interfaces.BankUserInformation;
import fr.teama.balanceservice.models.BankAccount;
import fr.teama.balanceservice.models.BankUser;
import fr.teama.balanceservice.models.Card;
import fr.teama.balanceservice.repository.BankAccountRepository;
import fr.teama.balanceservice.repository.BankUserRepository;
import fr.teama.balanceservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankAccountManager implements BankUserInformation {

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    CardRepository cardRepository;


    @Override
    public BankUser getBankUser(String email, String password) throws BankAccountNotFoundException, InvalidAccountPasswordException {
        BankUser user = bankUserRepository.findByEmail(email);
        if (user == null) {
            throw new BankAccountNotFoundException();
        } else if (!user.getPassword().equals(password)) {
            throw new InvalidAccountPasswordException(email);
        }
        return user;
    }

    @Override
    public BankAccount getBankAccountByCard(CardDTO bankUserCard) throws InvalidCardException, BankAccountNotFoundException {
        Card card = cardRepository.findByCardNumber(bankUserCard.getCardNumber());
        if (!card.getCvv().equals(bankUserCard.getCvv()) && !card.getExpirationDate().equals(bankUserCard.getExpirationDate())) {
            throw new InvalidCardException();
        }
        BankAccount account = card.getBankAccount();
        if (account == null) {
            throw new BankAccountNotFoundException();
        }
        return account;
    }


}
