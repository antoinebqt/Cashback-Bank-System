package fr.teama.balanceservice.controllers;

import fr.teama.balanceservice.controllers.dto.ErrorDTO;
import fr.teama.balanceservice.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {BalanceController.class})
public class GlobalControllerAdvice {

    @ExceptionHandler({BankAccountNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleExceptions(BankAccountNotFoundException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Bank account not found");
        errorDTO.setDetails("No bank account found with " + e.getField() + " " + e.getIban());
        return errorDTO;
    }

    @ExceptionHandler({InvalidAccountPasswordException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleExceptions(InvalidAccountPasswordException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Invalid password");
        errorDTO.setDetails("Invalid password for bank account " + e.getEmail());
        return errorDTO;
    }

    @ExceptionHandler({BankUserWithEmailAlreadyExistException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleExceptions(BankUserWithEmailAlreadyExistException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Bank user already exist");
        errorDTO.setDetails("Bank user with email " + e.getEmail() + " already exist");
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

    @ExceptionHandler({InvalidCardException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleExceptions(InvalidCardException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Invalid card");
        errorDTO.setDetails("Invalid given card");
        return errorDTO;
    }

}
