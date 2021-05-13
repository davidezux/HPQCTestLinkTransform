package mx.indra.hpqctestlink.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.indra.hpqctestlink.beans.Step;
import mx.indra.hpqctestlink.beans.MTCP;
import mx.indra.hpqctestlink.beans.TestCase;

public class HPQCXLSProcessServiceImpl implements HPQCXLSProcessService {

	private static final Logger LOG = LoggerFactory.getLogger(HPQCXLSProcessServiceImpl.class);

	public MTCP processXLS(File excelFile) throws Exception {
	
		MTCP mtcp = new MTCP();

		ArrayList<TestCase> testCases = new ArrayList<TestCase>();

		try {
			LOG.info("PROCESANDO ARCHIVO : " + excelFile.getName());
			FileInputStream inputStream = new FileInputStream(excelFile);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(1);

			LOG.info("TOTAL DE FILAS : " + firstSheet.getLastRowNum());
			Iterator<Row> iterator = firstSheet.iterator();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				// System.out.println("FILA: " + nextRow.getRowNum() );

				if (nextRow.getRowNum() >= 2) {

					TestCase testCase = new TestCase();
					ArrayList<Step> listSteps = new ArrayList<Step>();

					while (cellIterator.hasNext()) {

						Cell celldata = (Cell) cellIterator.next();
						int columnIndex = celldata.getColumnIndex();
						int rowIndex = celldata.getRowIndex();
						// System.out.print(rowIndex + " " + columnIndex +"-");
						switch (celldata.getCellType()) {
						case STRING:

							// GEENERAR LISTA DE ACCIONES (PASOS)
							if (columnIndex == 0) {
								testCase.setSummary(celldata.getStringCellValue());

							} else if (columnIndex == 3) {
								testCase.setName(celldata.getStringCellValue());
								// System.out.print(celldata.getStringCellValue());
							} else if (columnIndex == 4) {

								StringTokenizer tokens = new StringTokenizer(celldata.getStringCellValue(), "\n");
								int i =0;
								while (tokens.hasMoreTokens()) {
									Step step = new Step();
									step.setStepNumber(new Long(i));
									step.setActions(tokens.nextToken());
									listSteps.add(step);
									i++;
								}

							} else if (columnIndex == 5) {

								for (int i = 0; i < listSteps.size(); i++) {
									//listSteps.get(i).setStepNumber(new Long(i));
									listSteps.get(i).setExpectedResults(celldata.getStringCellValue());
								}

							} else if (columnIndex == 6) {
								testCase.setPreconditions(celldata.getStringCellValue());
								// System.out.print(celldata.getStringCellValue());
							} else if (columnIndex == 7) {
								testCase.setExecutionType(celldata.getStringCellValue());
								// System.out.print(celldata.getStringCellValue());
							} else if (columnIndex == 8) {
								testCase.setImportance(celldata.getStringCellValue());
								// System.out.print(celldata.getStringCellValue());
							} else if (columnIndex == 9) {
								
								for (int i = 0; i < listSteps.size(); i++) {
									listSteps.get(i).setExecutionType2(celldata.getStringCellValue());
								}
								
							}else if (columnIndex == 10) {
								testCase.setEstimatedExecDuration(celldata.getStringCellValue());
								// System.out.print(celldata.getStringCellValue());
							}

							break;
						case NUMERIC:
							// System.out.print(celldata.getNumericCellValue());
							break;
						case BOOLEAN:
							// System.out.print(celldata.getBooleanCellValue());
							break;
						case BLANK:
							// System.out.print(" ");
						default:
							// System.out.print(" - ");
						}

					}

					testCase.setSteps(listSteps);
					testCase.setVersion("Ciclo01");
					testCases.add(testCase);
				}

			}

			workbook.close();
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		mtcp.setTestCases(testCases);

		return mtcp;
	}

}
