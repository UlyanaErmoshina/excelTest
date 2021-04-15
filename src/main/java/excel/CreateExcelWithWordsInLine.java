package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class CreateExcelWithWordsInLine {

    public static void main(String[] args) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Words");

        int rowNum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rowNum);


        String[] words = {"съешь", "же", "ещё", "этих", "мягких", "французских", "булок", "да", "выпей", "чаю"};

        for (int j = 0; j < words.length; j++) {
            cell = row.createCell(j, CellType.NUMERIC);

            cell.setCellValue(words[j]);


            File file = new File("C:\\ved\\Wordsnew.xls");
            file.getParentFile().mkdirs();

            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());

        }
    }
}


