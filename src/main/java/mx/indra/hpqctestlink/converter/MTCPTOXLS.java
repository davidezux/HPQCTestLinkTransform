package mx.indra.hpqctestlink.converter;

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

import mx.indra.hpqctestlink.beans.MTCP;

public class MTCPTOXLS {
	
	private static final Logger LOG = LoggerFactory.getLogger(MTCPTOXLS.class);
	
	public static Boolean buildXLS(MTCP mtcp,File excelFile) throws Exception {
		

		  String[] headers = { "name", "version", "summary", "preconditions", "execution_type", "importance",
					"estimated_exec_duration", "step_number", "actions", "expectedresults", "execution_type2" };
		  List<String> titles = Arrays.asList(headers);
		  String sheetTitle = excelFile.getName().split("\\.")[0];
	        
		try {
			LOG.info("GENERANDO XLS");
			
			
		    XSSFWorkbook workbook = (XSSFWorkbook) getWorkbook(excelFile);
			XSSFSheet sheet = workbook.createSheet(sheetTitle);
			
			buildHeader(workbook,sheet,titles);
			
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
			 
			for (int i = 0; i < mtcp.getTestCases().size(); i++) {
				

			for (int column = 0 ; column < headers.length; column++ ) {
				//System.out.println("title: " + title);
				
			   for(int j=0; j < mtcp.getTestCases().get(i).getSteps().size(); j++) {
					
					Row row = sheet.createRow(++rowCount);
					
					Cell cell = row.createCell(column);
					cell.setCellValue((String) mtcp.getTestCases().get(i).getName());
					cell.setCellStyle(cellStyle);

					
					System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).getStepNumber());
					System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).getActions());
					System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).getExpectedResults());
					System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).getExecutionType2());
				}
				
				
				
				
			}
			
			
			}
			
			/*int rowCount = 0;
			
			for (int i = 0; i < mtcp.getTestCases().size(); i++) {
				
				int columnCount = 0;
				
				System.out.println("Test case : " + i);
				System.out.println("name : " + mtcp.getTestCases().get(i).getName());
				System.out.println("version : " + mtcp.getTestCases().get(i).getVersion());
				System.out.println("summary : " + mtcp.getTestCases().get(i).getSummary());
				System.out.println("preconditions: " + mtcp.getTestCases().get(i).getPreconditions());
				System.out.println("executionType: " + mtcp.getTestCases().get(i).getExecutionType());
				System.out.println("importance: " + mtcp.getTestCases().get(i).getImportance());
				System.out.println("estimatedExecDuration: " + mtcp.getTestCases().get(i).getEstimatedExecDuration());
				System.out.println("steps: ");

				for(int j=0; j < mtcp.getTestCases().get(i).getSteps().size(); j++) {
					
					Row row = sheet.createRow(++rowCount);
					
					Cell cell = row.createCell(columnCount++);
					
					System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).getStepNumber());
					System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).getActions());
					System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).getExpectedResults());
					System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).getExecutionType2());
				}
			
			}*/
			 
			writeWorkbook(workbook,excelFile);
				
			/*List<TestCase> testCasesLink = new ArrayList<TestCase>();
			for (mx.indra.hpqctestlink.beans.TestCase testCase : mtcp.getTestCases()) {
				TestCase testCaseLink = new TestCase();
				testCaseLink.setEstimatedExecDuration(testCase.getEstimatedExecDuration());
				testCaseLink.setExecutionType(testCase.getExecutionType());
				testCaseLink.setImportance(testCase.getImportance());
				testCaseLink.setName(testCase.getName());
				testCaseLink.setPreconditions(testCase.getPreconditions());
				testCaseLink.setSummary(testCase.getSummary());
				testCaseLink.setVersion(testCase.getVersion());
				List<Step> steps = new ArrayList<Step>();
				for (mx.indra.hpqctestlink.beans.Step step : testCase.getSteps()) {
					Step stepLink = new Step();
					stepLink.setActions(step.getActions());
					stepLink.setExecutionType(step.getExecutionType2());
					stepLink.setExpectedresults(step.getExpectedResults());
					stepLink.setStepNumber(step.getStepNumber().intValue());
					steps.add(stepLink);
				}
				testCaseLink.setSteps(steps);
				testCasesLink.add(testCaseLink);
			}*/
			
			//new FileOutputStream(outPutPath)

			 
			LOG.info("XLS GENERADO CON EXITO");
		} catch (Exception e) {
			LOG.info("Whops hubo un error - processXLS - : " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return true;
		
	}
	
	public static void buildHeader(XSSFWorkbook workbook ,Sheet sheet, List<String> titles) {

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

		byte[] byteColor = new byte[]{(byte) 279,(byte) 129,(byte) 189};
		XSSFColor color = new XSSFColor(byteColor, null);
		
		cellStyle.setFillForegroundColor(color);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setFont(headerFont);
		
		for (String title : titles) {
			System.out.println("title: " + title);
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
	    if (excelFile.getName().endsWith("xlsx")) {
	        workbook = new XSSFWorkbook();
	    } else if (excelFile.getName().endsWith("xls")) {
	        workbook = new HSSFWorkbook();
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	    return workbook;
	}
	
	public static void writeWorkbook(Workbook workbook,File excelFile) throws Exception {

	    if (excelFile.getName().endsWith("xlsx")) {
	    	
	    	 String outputPath = excelFile.getParent() + "/" + excelFile.getName().split("\\.")[0] + "_tl.xlsx";
	    	 FileOutputStream outputStream = new FileOutputStream(outputPath);
			 workbook.write(outputStream);
			 workbook.close();
			 
	    } else if (excelFile.getName().endsWith("xls")) {
	    	String outputPath = excelFile.getParent() + "/" + excelFile.getName().split("\\.")[0] + "_tl.xls";
	    	
	    	 FileOutputStream outputStream = new FileOutputStream(outputPath);
			 workbook.write(outputStream);
			 workbook.close();
			 
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	}
	
}
