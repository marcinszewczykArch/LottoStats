import Exceptions.DataImportException;

import java.sql.Date;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Main {
    public static void main(String[] args) throws DataImportException {
        String FILE_PATH = "D:\\Java\\IdeaProjects\\zadania\\StatystykiLotto\\src\\source.txt";
        DrawFromTxt drawFromTxt = new DrawFromTxt();
        ArrayList<Draw> lotteryHistory = drawFromTxt.importDrawDatabase(FILE_PATH);

        printDraws(lotteryHistory);
        printTopNNumbers(lotteryHistory, 5);
    }

    private static void printTopNNumbers(ArrayList<Draw> lotteryHistory, int N) {
        Map<Integer, Integer> lotteryStats = new HashMap<>();

        for (Draw draw : lotteryHistory) {
            for (int number : draw.getNumbers()) {
                lotteryStats.merge(number, 1, (a, b) -> Integer.sum(a, b));
            }
        }

        Map<Integer, Integer> sortedLotteryStatsTopTen = lotteryStats
                .entrySet()
                .stream()
                .sorted(comparingByValue(Comparator.reverseOrder()))
                .limit(N)
                .collect( toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        System.out.println("\nnajpopularniejsze " + N + " liczb(y) w losowaniach: ");
        sortedLotteryStatsTopTen.forEach((key, value) -> System.out.println(key + " - " + value + " razy"));
    }

    private static void printDraws(ArrayList<Draw> lotteryHistory) {
        for (Draw draw : lotteryHistory) {
            System.out.println(draw);
        }
    }
}
