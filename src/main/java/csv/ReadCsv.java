package csv;

import java.io.FileReader;
import java.util.Arrays;
import au.com.bytecode.opencsv.CSVReader;

public class ReadCsv {

    public static void readWordsFile(String[] args) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/Words.csv"), ',' , '"' , 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                System.out.println(Arrays.toString(nextLine));
            }
        }
    }

    public static String[] readNumbersFile()  throws Exception {
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/NumbersFile.csv"), ',' , '"' , 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                System.out.println(Arrays.toString(nextLine));
            }
        }

        return nextLine;
    }

    }



