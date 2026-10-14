package pl.pwr.atm;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {
    private BigDecimal balance = new BigDecimal("0.00");

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(validateAmount(amount));
    }

    public void withdraw(BigDecimal amount) {
        BigDecimal validAmount = validateAmount(amount);
        if (balance.compareTo(validAmount) < 0) {
            throw new IllegalArgumentException("Brak wystarczających środków.");
        }
        balance = balance.subtract(validAmount);
    }

    private BigDecimal validateAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Kwota musi być dodatnia.");
        }
        try {
            return amount.setScale(2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException("Kwota może mieć najwyżej dwa miejsca po przecinku.", exception);
        }
    }
}
