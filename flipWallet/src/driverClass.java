import dao.TransactionDao;
import dao.UserDao;
import dao.WalletDao;
import service.TransactionService;
import service.UserSevice;
import service.WalletService;

import java.math.BigDecimal;

public class driverClass {

    public static void main(String[] args){

        UserDao userDao = new UserDao();
        WalletDao walletDao = new WalletDao();
        TransactionDao transactionDao = new TransactionDao();
        UserSevice userSevice = new UserSevice(userDao);
        TransactionService transactionService = new TransactionService(transactionDao);
        WalletService walletService = new WalletService(walletDao, transactionService);

        userSevice.registerUser("Anmol");
        walletService.topupWallet("Anmol", "CC", BigDecimal.valueOf(12.12));

        userSevice.registerUser("Sid");

        userSevice.registerUser("Divya");

        walletService.fetchBalance("Anmol");

        walletService.sendMoney("Anmol","Sid", BigDecimal.valueOf(5.0));

        walletService.sendMoney("Anmol","Divya", BigDecimal.valueOf(2.0));

        walletService.sendMoney("Divya","Sid", BigDecimal.valueOf(2.0));

        walletService.fetchBalance("Sid");

        walletService.fetchBalance("Anmol");

        transactionService.getTransactions("Anmol", "SEND","AMOUNT");

        transactionService.getTransactions("Divya", "SEND","AMOUNT");

        transactionService.getTransactions("Sid", "RECEIVE","AMOUNT");

    }
}
