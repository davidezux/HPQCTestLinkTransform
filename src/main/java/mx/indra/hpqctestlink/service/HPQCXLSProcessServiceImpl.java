package mx.indra.hpqctestlink.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.indra.hpqctestlink.beans.MTCP;
import mx.indra.hpqctestlink.beans.Step;
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

			LOG.info("TOTAL DE REGISTROS : " + firstSheet.getLastRowNum());
			Iterator<Row> iterator = firstSheet.iterator();
		
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				// System.out.println("FILA: " + nextRow.getRowNum() );

				if (nextRow.getRowNum() >= 2) {

					TestCase testCase = new TestCase();
					ArrayList<Step> listSteps = new ArrayList<Step>();
					System.out.println("");
					while (cellIterator.hasNext()) {

						Cell celldata = (Cell) cellIterator.next();
						int columnIndex = celldata.getColumnIndex();
						//int rowIndex = celldata.getRowIndex();
						//System.out.print(rowIndex + "-" + columnIndex +" ");
						switch (celldata.getCellType()) {
						case STRING:

							// GEENERAR LISTA DE ACCIONES (PASOS)
							if (columnIndex == 0) {
								testCase.setSummary(celldata.getStringCellValue());
							} else if (columnIndex == 1) {
								String summary = testCase.getSummary() +"_"+ celldata.getStringCellValue();
								testCase.setSummary(summary);
							} else if (columnIndex == 2) {
								
								testCase.setName(getTestID(celldata.getStringCellValue()));
								testCase.setNumber(Long.valueOf(celldata.getStringCellValue()));
								
								
							} else if (columnIndex == 3) {
								String name = testCase.getName() + "_"+  celldata.getStringCellValue();
								testCase.setName(name);
								// System.out.print(celldata.getStringCellValue());
							} else if (columnIndex == 4) {
								StringTokenizer tokens = new StringTokenizer(celldata.getStringCellValue(), "\n");
								
								//System.out.println("tokens: " + tokens.countTokens());
								if(tokens.countTokens()==1) {
									
									List<String> headers = new ArrayList<String>();
									List<String> steps = new ArrayList<String>();
									List<String> subSteps = new ArrayList<String>();
									
									while (tokens.hasMoreTokens()) {
										String line = tokens.nextToken();
										
										//System.out.println("line: " + line);
					
										headers.add(line);
										
									}
									
									listSteps = getSteps(headers, steps,subSteps);
									
									
								}else {
								
								
								List<String> headers = new ArrayList<String>();
								List<String> steps = new ArrayList<String>();
								List<String> subSteps = new ArrayList<String>();

								String aux = "";
		
								while (tokens.hasMoreTokens()) {
									String line = tokens.nextToken();
									
									//System.out.println("line: " + line.trim().charAt(0));
									
									if (Character.isDigit(line.trim().charAt(0))) {
										// System.out.println(line);
										steps.add(line);
			                            aux="";
										
									} else if(line.trim().startsWith("*") || line.trim().startsWith("-")){
										
										int currentStep = steps.size();
										aux = steps.get(currentStep-1) + line.concat(",");
										steps.set(currentStep-1, aux);
										
										subSteps.add(line);
									}else {
										headers.add(line);
									}
								}

								listSteps = getSteps(headers, steps, subSteps);
								
								}

							} else if (columnIndex == 5) {

								for (int i = 0; i < listSteps.size(); i++) {
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

							} else if (columnIndex == 10) {
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
					
					//System.out.print("listSteps " + listSteps.isEmpty());
					if(!listSteps.isEmpty()) {
					testCases.add(testCase);
					}
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

	// FUNCION ENCARGADA DE AJUSTAR EL ENCABEZADO DE PASOS SI EL ENCABEZADO CONTIENE
	// MAS DE UNA DESCRIPCION
	public ArrayList<Step> getSteps(List<String> headers, List<String> steps, List<String> subSteps) {

		ArrayList<Step> listSteps = new ArrayList<Step>();
		String header = "";
		String aux = "";
		
		//System.out.println("HEADERS SIZE:" + headers.size());

		if (headers.size() >= 2) {
			for (int i = 0; i < headers.size(); i++) {
				if (i != headers.size() - 1) {
					aux += headers.get(i).substring(0, headers.get(i).length() - 1).concat(",");
				} else {
					header += aux + headers.get(i);
					//System.out.println("aux:" + aux);
					//System.out.println("header:" + header);
				}
			}

			Step step = new Step();
			step.setStepNumber(new Long(0));
			step.setActions(header);
			listSteps.add(step);
		} else {

			Step step = new Step();
			step.setStepNumber(new Long(0));
			step.setActions(headers.get(0));
			listSteps.add(step);
		}
	
		// System.out.println("STEPS SIZE:" + steps.size());

		for (int i = 0; i < steps.size(); i++) {
			Step step = new Step();
			step.setStepNumber(new Long(i) + 1);
			step.setActions(steps.get(i));
			listSteps.add(step);
		}

		/*System.out.println("SUB STEPS SIZE:" + subSteps.size());
		
		for (int i = 0; i < subSteps.size(); i++) {
			System.out.println("substep: " + subSteps.get(i));
		}*/
		
		/*
		 * for(int i=0; i < listSteps.size(); i++) {
		 * System.out.println(listSteps.get(i).getStepNumber());
		 * System.out.println(listSteps.get(i).getActions()); }
		 */

		return listSteps;
	}

	public String getTestID(String testId) {
		
		Long idNumber = Long.valueOf(testId);
		
		String prefix = "";
		
		//System.out.println("IdPrueba:" + idNumber);
		
		if(idNumber<=9) {
			prefix= "CP00" + String.valueOf(idNumber);
		}else if(idNumber<=99) {
			prefix= "CP0" + String.valueOf(idNumber);
		}else if(idNumber<=999) {
			prefix= "CP" + String.valueOf(idNumber);
		}
		
	   return prefix;
	}
	
	
}
