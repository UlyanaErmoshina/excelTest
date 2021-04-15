package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class CreateExcelWithWordsInColumn {


    public static void createExcelWithWords (String[] args) throws IOException {
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
