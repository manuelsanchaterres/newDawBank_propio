package main;

import domain.BankAccount;
import utils.AccountUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NewDamBank {

    public static void main(String[] args) {
//        IBAN PRUEBAS ES6621000418401234567891
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca IBAN Válido");
        String iban = scanner.nextLine();
        while(!AccountUtils.isValidIBAN(iban)){
            System.out.println("Introduzca IBAN Válido");
            iban = scanner.nextLine();
        }
        System.out.println("Introduzca Titular de Cuenta Válido");
        String accountHolderName = scanner.nextLine();
        while(!AccountUtils.isValidAccountHolder(accountHolderName)){
            System.out.println("Introduzca Titular de Cuenta Válido");
            accountHolderName = scanner.nextLine();
        }
//        SI EL IBAN Y TITULAR DE LA CUENTA SON VÁLIDOS, ENTONCES CREAMOS LA CUENTA (
        if(AccountUtils.isValidIBAN(iban) && AccountUtils.isValidAccountHolder(accountHolderName)){
            BankAccount bankAccount = new BankAccount(iban, accountHolderName);
            //System.out.println(bankAccount.toString());
            displayAccountOptions(bankAccount);
        }
    }

    private static void displayAccountOptions(BankAccount bankAccount){
        int accountOption=0;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("1. Datos de la cuenta. Mostrará el IBAN, el titular y el saldo.");
            System.out.println("2. IBAN. Mostrará el IBAN.");
            System.out.println("3. Titular. Mostrará el titular.");
            System.out.println("4. Saldo. Mostrará el saldo disponible.");
            System.out.println("5. Ingreso. Pedirá la cantidad a ingresar y realizará el ingreso si es posible.");
            System.out.println("6. Retirada. Pedirá la cantidad a retirar y realizará la retirada si es posible.");
            System.out.println("7. Movimientos. Mostrará una lista con el historial de movimientos.");
            System.out.println("8. Salir. Termina el programa.");
            accountOption = scanner.nextInt();
            switch (accountOption){

                case 1:
                    System.out.println("Datos de la Cuenta: \n" + "IBAN: " + bankAccount.getIban() + ", TITULAR: " + bankAccount.getAccountHolderName() + ", SALDO: " + bankAccount.getAccountBalance() + "\n");
                    break;
                case 2:
                    System.out.println("IBAN: \n" + bankAccount.getIban() + "\n");
                    break;
                case 3:
                    System.out.println("TITULAR: \n" + bankAccount.getAccountHolderName() + "\n");
                    break;
                case 4:
                    System.out.println("SALDO: \n" + bankAccount.getAccountBalance() + "\n");
                    break;
                case 5:
                    System.out.println("Introduzca Cantidad a Ingresar:");
                    // GESTIONAR INPUT DOUBLE ESCRITO CON PUNTO EN LUGAR DE COMA
                    try {
                        double depositAmount = scanner.nextDouble();
                        if (depositAmount == 0) {
                            System.out.println("La Cantidad a Ingresar debe ser Mayor a 0 euros.");
                        } else {

                            bankAccount.depositAmount(depositAmount);
                        }
                    } catch (InputMismatchException exception) { //TODO VERIFICAR PROPAGACIÓN EXCEPCIÓN CON MÁXIMO
                        System.out.println(exception.getMessage());
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Introduzca Cantidad a Retirar:");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount == 0) {
                        System.out.println("La Cantidad a Retirar debe ser Mayor a 0 euros.");
                    } else {

                        bankAccount.withdrawAmount(withdrawAmount);
                    }
                    break;
                case 7:
                    // TODO SHOW ACCOUNT TRANSACTIONS
                    bankAccount.showAccountTransactions();
                    break;
                case 8:
                    System.out.print("HAS SALIDO DE TU CUENTA.");
                    return;
                default:

                    // SI OPCIÓN INVÁLIDA, VUELVO A MOSTRAR OPCIONES DE CUENTA
                    System.out.println("SELECCIONE UNA OPCIÓN VÁLIDA.");
                    displayAccountOptions(bankAccount);
                    break;
            }
        }while(accountOption >= 1 && accountOption <= 8);
    }
}
