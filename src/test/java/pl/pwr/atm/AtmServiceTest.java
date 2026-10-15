package pl.pwr.atm;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtmServiceTest {
    private final AtmService atm =
            new AtmService(new Account());

    @Test
    void startsWithZeroBalance() {
        assertEquals(new BigDecimal("0.00"), atm.getBalance());
    }

    @Test
    void depositsAndWithdrawsMoney() {
        atm.deposit(new BigDecimal("100.50"));
        atm.withdraw(new BigDecimal("25.25"));
            assertEquals(new BigDecimal("75.25"), atm.getBalance());
    }

    @Test
    void canWithdrawWholeBalance() {
        atm.deposit(new BigDecimal("10"));
        atm.withdraw(new BigDecimal("10"));
        assertEquals(new BigDecimal("0.00"), atm.getBalance());
    }


    @Test
    void rejectsOverdraftWithoutChangingBalance() {
        atm.deposit(new BigDecimal("10"));
        assertThrows(IllegalArgumentException.class, () -> atm.withdraw(new BigDecimal("11")));
        assertEquals(new BigDecimal("10.00"), atm.getBalance());
    }

    @Test
    void rejectsInvalidAmountsWithoutChangingBalance() {
        BigDecimal[] invalidAmounts = {null, BigDecimal.ZERO, new BigDecimal("-1"), new BigDecimal("1.001")};
        for (BigDecimal amount : invalidAmounts) {
            assertThrows(IllegalArgumentException.class, () -> atm.deposit(amount));
            assertThrows(IllegalArgumentException.class, () -> atm.withdraw(amount));
        }
        assertEquals(new BigDecimal("0.00"), atm.getBalance());
    }

    @Test
    void addsDecimalAmountsExactly() {
        atm.deposit(new BigDecimal("0.10"));
        atm.deposit(new BigDecimal("0.20"));
        assertEquals(new BigDecimal("0.30"), atm.getBalance());
    }
}
