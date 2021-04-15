package excel;

import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.Assert;

public class CreateExcelAndCheck {

    public static void createExcel() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("excel.Text");
        Row row;
        String str = "съешь же ещё этих мягких французских булок, да выпей чаю";
        str = str.replaceAll(",", "");
        String[] words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            row = sheet.createRow(i);//создание строки под каждое слово
            Cell cell = row.createCell(0);//создание ячейки с нулевой позиции в колонку
            cell.setCellValue(words[i]);
            System.out.println(words[i]);

        }


        File file = new File("C:\\ved\\WordsT.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }

    public static String[] getText() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\ved\\WordsT.xls");
        Workbook wb = new HSSFWorkbook(fis); //открываем файл
        Sheet sheet = wb.getSheetAt(0); // берём страницу
        String str = "съешь же ещё этих мягких французских булок да выпей чаю";
        String[] words = str.split(" ");// смотрю что в массиве
        for (int j = 0; j < words.length; j++) { // смотрю что в массиве
            System.out.println(words[j]); // смотрю что в массиве
        }

        String[] text1 = new String[words.length];
        for (int i = 0; i < words.length; i++) { //обработка ячейки, цикл
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            text1[i] = cell.getStringCellValue();//присваиваем значение, заносим в массив
        }

        return text1;

    }


    public static String[] Split() {
        String str = "съешь же ещё этих мягких французских булок да выпей чаю";
        String[] words = str.split(" ");
        return words;
    }

    public static void DeleteExcel() throws IOException {

        File file = new File("C:\\ved\\WordsT.xls");
        file.delete();
    }

    public static int CellIsEmpty() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\ved\\WordsT.xls");
        Workbook wb = new HSSFWorkbook(fis); //открываем файл
        Sheet sheet = wb.getSheetAt(0);
        int filledRow = sheet.getPhysicalNumberOfRows();//кол-во заполненных строк

        int rowTotal = sheet.getLastRowNum();// общее кол-во строк
        if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) { // общее кол-во строк
            rowTotal++; // общее кол-во строк
        }
        int finalRow = rowTotal - filledRow;
        return filledRow;
    }


    public static void check(int expected) throws IOException {

        FileInputStream fis = new FileInputStream("C:\\ved\\WordsT.xls");
        Workbook wb = new HSSFWorkbook(fis); //открываем файл
        Sheet sheet = wb.getSheetAt(0);
        for (int i = 0; i < 10; i++) {

            int filledCells = sheet.getRow(i).getLastCellNum();
            int actual = filledCells;
            Assert.assertEquals(actual, expected);

        }
    }
}







