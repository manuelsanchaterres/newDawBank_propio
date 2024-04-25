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
// COMPROBAMOS SI LA CUANTÍA DEL MOVIMIENTO ES MAYOR A LA NOTIFICABLE A HACIENDA
        if (AccountUtils.checkTransactionAmount(transactionAmount, MAXIMUN_UNNOTIFIED_AMOUNT)) {
            System.out.println("\u001B[31m" + "AVISO: Notificar a hacienda." + "\u001B[0m");
        }
        accountBalance += transactionAmount;
        accountTransactions[currentTransactionsIndex] = transactionAmount;
        currentTransactionsIndex++;

        return;
    }

    public void withdrawAmount(double transactionAmount){
// SI LA TRANSACCIÓN DEJA SALDO POR DEBAJO DE -50 EUROS SE MUESTRA MENSAJE Y NO SE REALIZA LA RETIRADA


        if (accountBalance == MINIMUM_ACCOUNT_BALANCE) {

            System.out.println("\u001B[31m" + "AVISO: No puede Retirar Más Dinero." + "\u001B[0m");
        } else if (accountBalance - transactionAmount < MINIMUM_ACCOUNT_BALANCE) {

            System.out.println("\u001B[31m" + "AVISO: El Saldo de Su Cuenta No Puede Ser Inferior A -50 Euros." + "\u001B[0m");
            System.out.println("Retire Una Cantidad Inferior o Igual a " + (-MINIMUM_ACCOUNT_BALANCE + accountBalance) + " por favor.");
       } else {

            if (AccountUtils.checkTransactionAmount(transactionAmount, MAXIMUN_UNNOTIFIED_AMOUNT)) {
                System.out.println("\u001B[31m" + "AVISO: Notificar a hacienda." + "\u001B[0m");
            }
            accountBalance -= transactionAmount;
            accountTransactions[currentTransactionsIndex] = -transactionAmount;
            currentTransactionsIndex++;

            if (accountBalance >= MINIMUM_ACCOUNT_BALANCE && accountBalance < 0) {
                System.out.println("\u001B[31m" + "AVISO: Saldo negativo." + "\u001B[0m");
            }
       }

        return;
    }
    public void showAccountTransactions () {

        for (int i = 0; i < currentTransactionsIndex; i++) {

            System.out.println("MOVIMIENTO " + (i + 1) + ": " + accountTransactions[i] + " euros.");
        }
    }
}
