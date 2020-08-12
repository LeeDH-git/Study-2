package RealWorldSWDevelop.chapter3;

import java.time.Month;
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
}
