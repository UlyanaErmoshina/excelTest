import au.com.bytecode.opencsv.CSVReader;
import excel.Text3;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static sun.nio.ch.IOStatus.check;

public class TextTest {

    Cell cell;
    Row row;

    //@BeforeTest
    //void createNewExcel() throws IOException {

    //excel.Text3.createExcel();

    //}

    @Test
    void shouldTestWords() throws IOException {
        Text3.getText();

        String[] actual = Text3.getText();
        String[] expected = Text3.Split();

        Assert.assertEquals(actual, expected);
    }

    @Test
    void shouldTestCellIsEmptyBelow() throws IOException {
        Text3.getText();

        int actual = Text3.CellIsEmpty();//число заполненных строк
        int expected = 10;//длина массива
        Assert.assertEquals(actual, expected);

    }


    @Test
    void shouldTestCells() throws IOException {
        Text3.getText();

        //int filledCells = sheet.getRow(0).getLastCellNum();
        //System.out.println(filledCells);
        int expected = 1;//кол-во заполненных строк
        check(expected);

    }


    //Распарсить CSV файл вторую колонку и записать в excel файл с чередованием черерез одну пустю ячейку

    public static String[] parseCsvTest() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/New2.csv"), ',', '"', 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                System.out.println(Arrays.toString(nextLine));
            }
        }

        return nextLine;
    }


    //выводит на экран
    public static void parseCsvColumn2Test() throws Exception {

        File file = new File("src/main/resources/New2.csv");

        List<String> lines = Files.readAllLines(file.toPath(),
                StandardCharsets.UTF_8);

        for (String line : lines) {
            String[] array = line.split(",", -1);
            System.out.println(array[1]);
        }
    }


    //выводит на экран и возвращает массив
    public static String[] parseCsvColumn2Test2() throws Exception {

        File file = new File("src/main/resources/New2.csv");

        List<String> lines = Files.readAllLines(file.toPath(),
                StandardCharsets.UTF_8);

        String[] array = new String[lines.size()];
        String[][] array2 = new String[1001][2];



        for (String line : lines) {

            array = line.split(",", -1);
            System.out.println(array[1]);
        }
        return array;
    }


//чтение и вывод второй колонки
    public static String[] parseCsvColumn2Test3() throws Exception {
        String splitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/New2.csv"));
        String line = br.readLine();
        String[] b = new String[line.length()];
        while ((line = br.readLine()) != null) {
            b = line.split(splitBy);
            System.out.println(b);
        }
        return b;
    }






    //создаём excel и записываем массив с чередованием через пустую ячейку
    public static void createExcel() throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Numbers");


        //FileInputStream fis = new FileInputStream("C:\\ved\\Numbers.xls");
        //Workbook wb = new HSSFWorkbook(fis); //открываем файл
        //Sheet sheet = wb.getSheetAt(0); // берём страницу


        int rowNum = 0;


        String[] array = parseCsvColumn2Test2();
        //List<String> lines = parseCsvColumn2Test2();
        //String[] array = lines.toArray(new String[lines.size()]); //преобразование листа строк в массив, который ровняется длине этоого листа

        System.out.println(array.length);
        for (int i = 0; i < array.length; i++) { //обработка ячейки, цикл
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(array[i]);//пишем в каждую ячейку каждое значение из массива
        }

        File file = new File("C:\\ved\\Numbers.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }


    //@AfterTest
    //void deleteFile() throws IOException {
    //excel.Text3.DeleteExcel(); //вызов метода удаления файла
    //}


    @Test
    public void test5() throws Exception {
        parseCsvColumn2Test2();

    }
}






