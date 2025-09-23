package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public static String getCellData(String excelPath, int sheetNumber, int rowNumber, int colNumber)
			throws IOException {

		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(colNumber);
		String cellValue = "";
		if (cell != null) {
			cellValue = cell.toString();
		}
		workbook.close();
		fis.close();

		System.out.println("Cell Data is: " + cellValue);
		return cellValue;
	}

	public static void writeCellData(String excelPath, int sheetNumber, int rowNumber, int colNumber, String value)
			throws IOException {
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		Row row = sheet.getRow(rowNumber);
		if (row == null) {
			row = sheet.createRow(rowNumber);
		}
		Cell cell = row.getCell(colNumber);
		if (cell == null) {
			cell = row.createCell(colNumber);
		}
		cell.setCellValue(value);
		fis.close();
		FileOutputStream fos = new FileOutputStream(excelPath);
		workbook.write(fos);
		fos.close();
		workbook.close();
		System.out.println(value + " is written to Excel successfully.......");
	}

	public static String getBelowCellValue(String excelPath, String key, int sheetNumber, int rowIndex)
			throws IOException {
		String value = null;
		try (FileInputStream fis = new FileInputStream(excelPath); Workbook workbook = WorkbookFactory.create(fis)) {
			if (sheetNumber >= workbook.getNumberOfSheets()) {
				throw new IllegalArgumentException("Invalid sheet index: " + sheetNumber);
			}
			Sheet sheet = workbook.getSheetAt(sheetNumber);
			Row row = sheet.getRow(rowIndex);
			if (row == null) {
				throw new IllegalArgumentException("Row " + rowIndex + " is empty or does not exist.");
			}
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if (cell != null && cell.toString().equalsIgnoreCase(key)) {
					Row nextRow = sheet.getRow(rowIndex + 1);
					if (nextRow != null) {
						Cell nextCell = nextRow.getCell(j);

						if (nextCell != null) {
							value = nextCell.toString();
						}
					}
					break;
				}
			}
		}
		System.out.println("Below cell value to Key " + key + " is: " + value);
		return value;
	}

	public static String getAdjacentCellValue(String excelPath, String key, int sheetNumber, int columnNumber)
			throws IOException {
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		String result = null;

		for (Row row : sheet) {
			Cell cell = row.getCell(columnNumber);

			if (cell != null) {
				String cellValue = cell.toString().trim();

				if (cellValue.equalsIgnoreCase(key)) {
					Cell adjacentCell = row.getCell(columnNumber + 1);
					if (adjacentCell != null) {
						result = adjacentCell.toString();
					}
					break;
				}
			}
		}
		workbook.close();
		fis.close();
		System.out.println("Adjacent cell value to Key " + key + " is: " + result);
		return result;
	}

}
