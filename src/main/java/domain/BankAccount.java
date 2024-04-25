package domain;

import utils.AccountUtils;

import java.util.Arrays;

public class BankAccount {

    // ATRIBUTOS CONSTANTES CUENTA BANCARIA
    private final int MAX_ALLOWED_TRANSACTIONS = 100;
    private final int MINIMUM_ACCOUNT_BALANCE = -50;
    private final int MAXIMUN_UNNOTIFIED_AMOUNT = 3000;
    // ATRIBUTOS VARIABLES CUENTA BANCARIA
    private String iban = "";
    private String accountHolderName = "";
    private double accountBalance;
    private double[] accountTransactions;
    private int currentTransactionsIndex = 0;

    public BankAccount(String iban, String accountHolderName){
        this.iban = iban;
        this.accountHolderName = accountHolderName;
        this.accountBalance = 0.0;
        this.accountTransactions = new double[MAX_ALLOWED_TRANSACTIONS];
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "MAX_ALLOWED_TRANSACTIONS=" + MAX_ALLOWED_TRANSACTIONS +
                ", iban='" + iban + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", accountBalance=" + accountBalance +
                ", accountTransactions=" + Arrays.toString(accountTransactions) +
                ", currentTransactionsIndex=" + currentTransactionsIndex +
                '}';
    }

    public String getIban() {
        return iban;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void depositAmount(double transactionAmount){

        if (transactionAmount > 0) {

            if (AccountUtils.checkTransactionAmount(transactionAmount, MAXIMUN_UNNOTIFIED_AMOUNT)) {
                System.out.println("AVISO: Notificar a hacienda");
            }
            accountBalance += transactionAmount;
            accountTransactions[this.currentTransactionsIndex] = transactionAmount;
            currentTransactionsIndex++;

        } else {

            System.out.println("AVISO: No se pueden Ingresar Cantidades Negativas o igual a 0");
        }

        return;
    }
}
