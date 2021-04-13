package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
//todo в чем разница между классами text, text2 и text3 - не понятно
public class Text {
    //todo массив не используешь. Старайся не оставлять параметров серых после завершения написания кода
    String[] words = {"съешь", "же", "ещё", "этих", "мягких", "французских", "булок", ",", "да", "выпей", "чаю"};

    //todo не шлёпай везде main. При компиляции всего кода это приведет к ошибке, потому что у тебя несколько точек входа
    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Words");

        int rowNum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rowNum);


        // Word1
        row = sheet.createRow(0);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("съешь");

        // Word2
        row = sheet.createRow(1);
        cell = row.createCell(0, CellType.NUMERIC);
        cell.setCellValue("же");

        // Word3
        row = sheet.createRow(2);
        cell = row.createCell(0, CellType.NUMERIC);
        cell.setCellValue("ещё");

        // Word4
        row = sheet.createRow(3);
        cell = row.createCell(0, CellType.NUMERIC);
        cell.setCellValue("этих");

        // Word5
        row = sheet.createRow(4);
        cell = row.createCell(0, CellType.NUMERIC);
        cell.setCellValue("мягких");






        File file = new File("C:\\ved\\Words.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());



    }
}
