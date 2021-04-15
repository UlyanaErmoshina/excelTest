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


public class CreateExcelWithNumbers {

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


        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number");
        cell.setCellStyle(style);

        //цикл

        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        for (int j = 1; j <= numbers.length; j++) {
            cell = row.createCell(j, CellType.NUMERIC);

            cell.setCellValue(numbers[j-1]);

            if(j % 2 == 0) {
                cell.setCellStyle(style);
            }
        }


        //добавить if с чередованием шрифта

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
