package fr.teama.bankservice.controllers;

import fr.teama.bankservice.controllers.dto.ErrorDTO;
import fr.teama.bankservice.exceptions.BankAccountNotFoundException;
import fr.teama.bankservice.exceptions.NotEnoughMoneyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {BalanceController.class, BankUserController.class})
public class GlobalControllerAdvice {

    @ExceptionHandler({BankAccountNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleExceptions(BankAccountNotFoundException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Bank account not found");
        errorDTO.setDetails("No bank account found with " + e.getField() + " " + e.getIban());
        return errorDTO;
    }

    @ExceptionHandler({NotEnoughMoneyException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleExceptions(NotEnoughMoneyException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Not enough money");
        errorDTO.setDetails("Not enough money on account");
        return errorDTO;
    }

}
