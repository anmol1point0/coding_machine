package dao;

import java.math.BigDecimal;

public class DCProvidersDaoImpl implements ProvidersDao{
    @Override
    public Boolean getMoney(String userName, BigDecimal amount) {
        return true;
    }
}
