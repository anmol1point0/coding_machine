package comparator;

import model.Transaction;

import java.util.Comparator;

public class AmountBasedComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return t1.getAmount().compareTo(t2.getAmount());
    }
}