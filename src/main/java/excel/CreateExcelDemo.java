package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;


public class CreateExcelDemo {

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Numbers");

        int rowNum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);
        row = sheet.createRow(rowNum);

        // Number
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number");
        cell.setCellStyle(style);

        // Number1
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue("1");
        cell.setCellStyle(style);

        // Number2
        cell = row.createCell(2, CellType.NUMERIC);
        cell.setCellValue("2");

        // Number3
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue("3");
        cell.setCellStyle(style);

        // Number4
        cell = row.createCell(4, CellType.NUMERIC);
        cell.setCellValue("4");

        // Number5
        cell = row.createCell(5, CellType.NUMERIC);
        cell.setCellValue("5");
        cell.setCellStyle(style);

        // Number6
        cell = row.createCell(6, CellType.NUMERIC);
        cell.setCellValue("6");

        // Number7
        cell = row.createCell(7, CellType.NUMERIC);
        cell.setCellValue("7");
        cell.setCellStyle(style);

        // Number8
        cell = row.createCell(8, CellType.NUMERIC);
        cell.setCellValue("8");

        // Number9
        cell = row.createCell(9, CellType.NUMERIC);
        cell.setCellValue("9");
        cell.setCellStyle(style);

        // Number10
        cell = row.createCell(10, CellType.NUMERIC);
        cell.setCellValue("10");

        // Column1,15
        row = sheet.createRow(1);
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue("15");

        // Column1,16
        row = sheet.createRow(2);
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue("16");
        cell.setCellStyle(style);

        // Column1,17
        row = sheet.createRow(3);
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue("17");




        File file = new File("C:\\ved\\Num.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }
}
