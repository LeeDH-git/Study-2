package RealWorldSWDevelop.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzer {

    private static final String RESOURCES = "src/main/resources";

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get(RESOURCES + args[0]);
        // Path 클래스 : 파일 시스템의 경로
        final List<String> lines = Files.readAllLines(path); // Files.readAllLines : 행목록 반환
        double total = 0d;

        for (final String line : lines) {
            final String[] columns = line.split(","); // ,로 열 분리
            final double amount = Double.parseDouble(columns[1]); // 금액 추출 후 double로 파싱
            total += amount;
        }

        System.out.println("total : " + total);
    }
}
