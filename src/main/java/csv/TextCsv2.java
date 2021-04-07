package csv;

import java.io.FileReader;
import java.util.Arrays;
import au.com.bytecode.opencsv.CSVReader;

public class TextCsv2 {

    public static String[] main()  throws Exception {
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/New2.csv"), ',' , '"' , 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                System.out.println(Arrays.toString(nextLine));
            }
        }

        return nextLine;
    }
}
