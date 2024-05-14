package service;

import dao.*;
import enums.PROVIDERS;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletService {

    public final WalletDao walletDao;
    public final TransactionService transactionService;

    public WalletService(WalletDao walletDao,
                         TransactionService transactionService) {
        this.walletDao = walletDao;
        this.transactionService = transactionService;
    }


    public Boolean topupWallet(String userName, String provider, BigDecimal amount){
        if(Objects.isNull(userName)){
            System.out.println("User: " + userName + " cannot be null while topup");
        }
        if(Objects.isNull(provider)){
            System.out.println("Provider: " + provider + " cannot be null  while topup");
        }
        if(Objects.isNull(amount)){
            System.out.println("Amount: " + amount + " cannot be null  while topup");

        }

        ProvidersDao dao;

        if(provider.equals(PROVIDERS.CC.toString())){
             dao = new CCProvidersDaoImpl();
        }
        else if(provider.equals(PROVIDERS.DC.toString())){
            dao = new DCProvidersDaoImpl();
        }
        else if(provider.equals(PROVIDERS.UPI.toString())){
            dao = new UpiProviderDaoImpl();
        }
        else {
            System.out.println("No valid provider");
            return null;
        }

        Boolean isAmountPresent = dao.getMoney(userName, amount);

        if(Boolean.FALSE.equals(isAmountPresent)){
            System.out.println("Amount is not present in the provider: " + provider);
        }

        return walletDao.topUpWallet(userName, amount);
    }

    public void fetchBalance(String userName){
        if(Objects.isNull(userName)){
            //todo
        }

        BigDecimal currBalance = walletDao.fetchBalance(userName);
        System.out.println("CurrBalance of user: " + userName + " is  " + currBalance);
        return;
    }

    public Boolean sendMoney(String senderName, String recipientName, BigDecimal amount){
        if(Objects.isNull(senderName)){
            //todo
        }
        if(Objects.isNull(recipientName)){
            //todo
        }
        if(Objects.isNull(amount)){
            //todo
        }

        Long transactionTime = System.currentTimeMillis();

        if(amount.compareTo(BigDecimal.valueOf(0)) <= 0){
            System.out.println("Amount not present in user waller: " + senderName + " while sending it to: " +
                    recipientName + " at time: " + transactionTime);
        }

        Boolean isTransferSuccess = walletDao.sendMoney(senderName, recipientName, amount);

        if(Boolean.TRUE.equals(isTransferSuccess)){
            transactionService.recordTransaction(senderName, recipientName, amount, transactionTime);
        }
        else{
            //todo
        }
        return true;
    }
}
