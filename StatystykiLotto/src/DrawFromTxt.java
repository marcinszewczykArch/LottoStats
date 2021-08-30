import Exceptions.DataImportException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DrawFromTxt {


    public ArrayList<Draw> importDrawDatabase(String FILE_PATH) throws DataImportException {
        ArrayList<Draw> lotteryHistory = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            bufferedReader.lines()
                    .map(line -> createDrawFromLine(line))
                    .forEach(draw -> lotteryHistory.add(draw));
        } catch (FileNotFoundException e) {
            throw new DataImportException("there is no file with the name: " + FILE_PATH);
        } catch (IOException ioException) {
            throw new DataImportException("error while importing file with the name: " + FILE_PATH);
        }
        return lotteryHistory;
    }

    private Draw createDrawFromLine(String line) {
        List<String> draw = Arrays.asList(line.split(" "));

        String idString = draw.get(0);
        int id = Integer.parseInt(idString.substring(0, (idString.length()-1)));


        String dateString = draw.get(1);
                int day = Integer.parseInt(dateString.substring(0, 2));
                int month = Integer.parseInt(dateString.substring(3, 5));
                int year = Integer.parseInt(dateString.substring(6, 10));
                Date date = new Date(year, month, day);

        String numbersString = draw.get(2);
            List<String> numbersArrayString = Arrays.asList(numbersString.split(","));
            int[] numbers = new int[6];
            int i=0;
                for (String s : numbersArrayString) {
                    numbers[i] = Integer.parseInt(s);
                    i++;
                }

        return new Draw(id, date, numbers);
    }
}
