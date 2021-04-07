package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;


public class CreateExcelDemo2 {

    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Number");

        int rowNum = 0;
        Cell cell;
        Row row;


        //cell = row.createCell(rowNum, CellType.NUMERIC);
        for (int i = 0; i < 10; i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                cell = row.createCell(j, CellType.NUMERIC);

                cell.setCellValue(j);
            }
        }

            File file = new File("C:\\ved\\Nums.xls");
            file.getParentFile().mkdirs();

            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());

        }
    }



