package co.com.bancolombia.demo.exceptions;

public class InvalidBankAccountException extends RuntimeException{
    public InvalidBankAccountException(String message) {
        super(message);
    }
}
