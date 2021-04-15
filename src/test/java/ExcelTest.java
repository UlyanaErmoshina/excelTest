import excel.CreateExcelAndCheck;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static sun.nio.ch.IOStatus.check;

public class ExcelTest {


    @Test
    void shouldTestWords() throws IOException {
        CreateExcelAndCheck.getText();

        String[] actual = CreateExcelAndCheck.getText();
        String[] expected = CreateExcelAndCheck.Split();

        Assert.assertEquals(actual, expected);
    }

    @Test
    void shouldTestCellIsEmptyBelow() throws IOException {
        CreateExcelAndCheck.getText();

        int actual = CreateExcelAndCheck.CellIsEmpty();//число заполненных строк
        int expected = 10;//длина массива
        Assert.assertEquals(actual, expected);

    }


    @Test
    void shouldTestCells() throws IOException {
        CreateExcelAndCheck.getText();

        int expected = 1;//кол-во заполненных строк
        check(expected);

    }


    //парсим CSV файл, возвращаем двумерный массив

    public static String[][] parseCsv() throws Exception {

        File file = new File("src/main/resources/NumbersFile.csv");

        List<String> lines = Files.readAllLines(file.toPath(),
                StandardCharsets.UTF_8);

        String[][] array2 = new String[lines.size()][2];
        int index = 0;
        for (String line : lines) {
            array2[index] = line.split(",");
            index++;
        }
        for (int i = 0; i < 1001; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(" " + array2[i][j] + " ");
            }
            System.out.println();
        }

        return array2;

    }


    //создаём excel и записываем массив с данными второй колонки с чередованием через пустую ячейку
    public static void createExcelAndWriteSecondColumn() throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Numbers");

        String[][] array2 = parseCsv();

        System.out.println(array2.length);
        for (int i = 0; i < array2.length; i++) { //обработка ячейки, цикл
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(array2[i][1]);//пишем в каждую ячейку каждое значение из массива
        }

        File file = new File("C:\\ved\\Numbers.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }


    @Test
    public void parseCsvAndCreateExcel() throws Exception {
        createExcelAndWriteSecondColumn();

    }


    @AfterTest
    void deleteFile() throws IOException {
    excel.CreateExcelAndCheck.DeleteExcel(); //вызов метода удаления файла,если он нужен
    }

}







