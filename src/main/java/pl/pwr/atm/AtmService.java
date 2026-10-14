package pl.pwr.atm;

import java.math.BigDecimal;

public class AtmService {
    private final Account account;

    public AtmService(Account account) {
        this.account = account;
    }

    public BigDecimal getBalance() {
        return account.getBalance();
    }

    public void deposit(BigDecimal amount) {
        account.deposit(amount);
    }

    public void withdraw(BigDecimal amount) {
        account.withdraw(amount);
    }
}
