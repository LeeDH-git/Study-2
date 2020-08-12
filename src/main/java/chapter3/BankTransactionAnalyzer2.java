package chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzer2 {

    private static final String RESOURCES = "src/main/resources";

    private static final BankStatementCSVParser CSVParser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {

        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path); // Files.readAllLines : 행목록 반환

        final List<BankTransaction> bankTransactions = CSVParser.parseLinesFromCSV(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {

        System.out.println("The total for all transaction is " + bankStatementProcessor.calculatorTotalAmount());
        System.out.println("Transaction in January " + bankStatementProcessor.calculateInMonth(Month.JANUARY));
        System.out.println("Transaction in January " + bankStatementProcessor.calculateInMonth(Month.FEBRUARY));
        System.out.println("The total salary received is " + bankStatementProcessor.calculateTotalCategory("Salary"));

    }

}
