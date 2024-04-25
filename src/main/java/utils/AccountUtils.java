package utils;

public class AccountUtils {

    public static boolean isValidIBAN(String iban){

        return iban.matches("^[A-Z]{2}\\d{22}");
    }

    public static boolean isValidAccountHolder(String accountHolderName){

        return accountHolderName.matches("^[a-zA-Z][a-zA-Z\\s]+[a-zA-Z]$");
    }

    public static boolean checkTransactionAmount(double transactionAmount, int transactionAmountNotify){

        return transactionAmount > transactionAmountNotify;
    }
}
