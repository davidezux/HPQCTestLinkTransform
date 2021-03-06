package mx.indra.hpqctestlink.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.indra.hpqc.testlink.beans.xls.XLSRoot;
import mx.indra.hpqc.testlink.beans.xls.XLSStep;
import mx.indra.hpqc.testlink.beans.xls.XLSTestCaseRoot;
import mx.indra.hpqctestlink.beans.MTCP;
import mx.indra.hpqctestlink.converter.MTCPTOXLSRoot;

public class BuildXLSServiceImpl implements BuildXLSService {

	private static final Logger LOG = LoggerFactory.getLogger(BuildXLSServiceImpl.class);

	public boolean buildXLS(XLSRoot xlsRoot, File excelFile) throws Exception {
		
		// LISTA DE ENCABEZADOS 
		
		String[] headers = { "name", "version", "summary", "preconditions", "execution_type", "importance",
				"estimated_exec_duration", "step_number", "actions", "expectedresults", "execution_type2" };
		List<String> titles = Arrays.asList(headers);
		String sheetTitle = excelFile.getName().split("\\.")[0];

		try {
			LOG.info("GENERANDO XLS");

			XSSFWorkbook workbook = (XSSFWorkbook) getWorkbook(excelFile);
			XSSFSheet sheet = workbook.createSheet(sheetTitle);
            
			// SE GENERA EL ENCABEZADO DEL EXCEL 
			
			buildHeader(workbook, sheet, titles);

			XSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.LEFT);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			XSSFFont contentFont = workbook.createFont();
			contentFont.setColor(IndexedColors.BLACK.index);
			contentFont.setFontName("Arial");
			contentFont.setFontHeight(10);
			contentFont.setBold(false);
			cellStyle.setFont(contentFont);

			int rowCount = 0;

			for (XLSStep step : xlsRoot.getXlsSteps()) {
				Row row = sheet.createRow(++rowCount);

				Cell cell = row.createCell(0);
				cell.setCellValue(step.getName());
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell(1);
				cell.setCellValue(step.getVersion());
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell(2);
				cell.setCellValue(step.getSummary());
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell(3);
				cell.setCellValue(step.getPreconditions());
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell(4);
				cell.setCellValue(step.getExecutionType());
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell(5);
				cell.setCellValue(step.getImportance());
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell(6);
				cell.setCellValue(step.getEstimatedExecDuration());
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell(7);
				cell.setCellValue(step.getStepNumber());
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell(8);
				cell.setCellValue(step.getActions());
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell(9);
				cell.setCellValue(step.getExpectedResults());
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell(10);
				cell.setCellValue(step.getExecutionType2());
				cell.setCellStyle(cellStyle);
			}

			for (int i = 0; i < xlsRoot.getXlsSteps().size(); i++) {
				sheet.autoSizeColumn(i);
			}

			writeWorkbook(workbook, excelFile);

			LOG.info("XLS GENERADO CON EXITO");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	public boolean processTestCases(MTCP mtcp, File excelFile) throws Exception {
		buildXLS(MTCPTOXLSRoot.processTestCases(mtcp), excelFile);
		return false;
	}

	public static void buildHeader(XSSFWorkbook workbook, Sheet sheet, List<String> titles) {

		Row row = sheet.createRow(0);

		int columnCount = 0;

		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		XSSFFont headerFont = workbook.createFont();
		headerFont.setColor(IndexedColors.WHITE.index);
		headerFont.setFontName("Arial");
		headerFont.setFontHeight(10);
		headerFont.setBold(false);

		byte[] byteColor = new byte[] { (byte) 279, (byte) 129, (byte) 189 };
		XSSFColor color = new XSSFColor(byteColor, null);

		cellStyle.setFillForegroundColor(color);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setFont(headerFont);

		for (String title : titles) {
			// System.out.println("title: " + title);
			Cell cell = row.createCell(columnCount++);
			cell.setCellValue((String) title);
			cell.setCellStyle(cellStyle);

		}

		for (int i = 0; i < titles.size(); i++) {
			sheet.autoSizeColumn(i);
		}

	}

	public static Workbook getWorkbook(File excelFile) throws Exception {
		Workbook workbook = null;
		if (excelFile.getName().endsWith("xlsx") || excelFile.getName().endsWith("xlsm")) {
			workbook = new XSSFWorkbook();
		} else if (excelFile.getName().endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	public static void writeWorkbook(Workbook workbook, File excelFile) throws Exception {

		if (excelFile.getName().endsWith("xlsx") || excelFile.getName().endsWith("xlsm")) {

			String outputPath = excelFile.getParent() + "/" + excelFile.getName().split("\\.")[0] + "_tl.xlsx";
			File outputFile = new File(outputPath);
			if (outputFile.exists()) {
				if (outputFile.delete()) {
					LOG.info("El fichero existente se elimino con exito");
				} else {
					LOG.info("El fichero existente no pudo ser eliminado");
				}
			}

			FileOutputStream outputStream = new FileOutputStream(outputPath);
			workbook.write(outputStream);
			workbook.close();

		} else if (excelFile.getName().endsWith("xls")) {
			String outputPath = excelFile.getParent() + "/" + excelFile.getName().split("\\.")[0] + "_tl.xls";

			FileOutputStream outputStream = new FileOutputStream(outputPath);
			workbook.write(outputStream);
			workbook.close();

		} else {
			throw new IllegalArgumentException("El archivo especificado no es un archivo de Excel valido");
		}

	}

	public static void printList(List<XLSTestCaseRoot> xlsTestCases) {

		for (int i = 0; i < xlsTestCases.size() ; i++) {

			for (XLSStep xlsStep : xlsTestCases.get(i).getXlsSteps()) {
				System.out.println("name : " + xlsStep.getName());
				System.out.println("version : " + xlsStep.getVersion());
				System.out.println("summary : " + xlsStep.getSummary());
				System.out.println("preconditions: " + xlsStep.getPreconditions());
				System.out.println("executionType: " + xlsStep.getExecutionType());
				System.out.println("importance: " + xlsStep.getImportance());
				System.out.println("estimatedExecDuration: " + xlsStep.getEstimatedExecDuration());
				System.out.println("stepNumber" + xlsStep.getStepNumber());
				System.out.println("actions" + xlsStep.getActions());
				System.out.println("expectedResults" + xlsStep.getExpectedResults());
				System.out.println("executionType2" + xlsStep.getExecutionType2());
			}
		}

	}

}
