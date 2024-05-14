package dao;

import java.math.BigDecimal;

public class CCProvidersDaoImpl implements ProvidersDao{
    @Override
    public Boolean getMoney(String userName, BigDecimal amount) {
        return true;
    }
}
