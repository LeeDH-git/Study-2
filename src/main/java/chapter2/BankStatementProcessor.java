package chapter2;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {

    private final List<BankTransaction> bkt;

    public BankStatementProcessor(final List<BankTransaction> bkt) {
        this.bkt = bkt;
    }

    public double calculatorTotalAmount() {
        double total = 0;
        for (final BankTransaction bankTransaction : bkt) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateInMonth(final Month month) {

        double total = 0;

        for (final BankTransaction bankTransaction : bkt) {
            if (bankTransaction.getDate().getMonth() == month) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalCategory(final String category) {

        double total = 0;

        for (final BankTransaction bankTransaction : bkt) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    // 특정 금액 거래내역 찾기
    public List<BankTransaction> findTransactionGreaterThanEqual(final int amount) {

        final List<BankTransaction> result = new ArrayList<BankTransaction>();
        for (final BankTransaction bankTransaction : bkt) {
            if (bankTransaction.getAmount() >= amount) {
                result.add(bankTransaction);
            }

        }
        return result;
    }

    // 특정월의 입출금 내역 찾기
    public List<BankTransaction> findTransactionInMonthGetter(final Month month, final int amount) {

        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction : bkt) {
            if (bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount) {
                result.add(bankTransaction);
            }

        }
        return result;
    }
}
