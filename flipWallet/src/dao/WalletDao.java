package dao;

import model.Transaction;
import model.Wallet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

public class WalletDao {

    private final HashMap<String, Wallet> userWallets;

    public WalletDao() {
        this.userWallets = new HashMap<>();
    }

    public Boolean topUpWallet(String userName, BigDecimal amount){
//        if(!userWallets.containsKey(userName)){
//            System.out.println("User: " + userName + " does not exist, can't topup");
//            return null;
//        }

        Wallet wallet = userWallets.get(userName);

        if(Objects.isNull(wallet)){
            wallet = new Wallet();
        }

        BigDecimal currBalance = wallet.getCurrBalance();

        if(Objects.isNull(currBalance)){
            currBalance = new BigDecimal(0);
        }

        System.out.println("User: " + userName + " topup wallet, increasing amoutn from " + currBalance + " to " +
                currBalance.add(amount));


        currBalance = currBalance.add(amount);
        wallet.setCurrBalance(currBalance);
        userWallets.put(userName, wallet);
        return true;
    }

    public BigDecimal fetchBalance(String userName){
        if(!userWallets.containsKey(userName)){
            System.out.println("userName: " + userName + " cannot be null  while topup");
            return null;
        }

        Wallet wallet = userWallets.get(userName);
        return wallet.getCurrBalance();
    }

    public Boolean sendMoney(String senderName, String recipientName, BigDecimal amount){
        if(!userWallets.containsKey(senderName)){
            //todo
        }

        if(!userWallets.containsKey(recipientName)){
            //todo
        }

        Wallet senderWallet = userWallets.get(senderName);
        if(Objects.isNull(senderWallet)){
            //todo
        }
        BigDecimal currentBalanceOfSender = senderWallet.getCurrBalance();

        if(currentBalanceOfSender.compareTo(amount) < 0){
            //todo
        }
        else{

            BigDecimal newBalanceOfSender = currentBalanceOfSender.subtract(amount);
            senderWallet.setCurrBalance(newBalanceOfSender);
            userWallets.put(senderName, senderWallet);

            Wallet recipientWallet = userWallets.get(recipientName);
            if(Objects.isNull(recipientWallet)){
                recipientWallet = new Wallet();
                recipientWallet.setCurrBalance(BigDecimal.valueOf(0));
            }

            BigDecimal currentBalanceOfRecipient = recipientWallet.getCurrBalance();
            BigDecimal newBalanceOfRecipient =currentBalanceOfRecipient.add(amount);
            recipientWallet.setCurrBalance(newBalanceOfRecipient);

            userWallets.put(recipientName, recipientWallet);
            System.out.println(senderName + " has transferred " + amount + " to " + recipientName);
        }
        return true;
    }
}
