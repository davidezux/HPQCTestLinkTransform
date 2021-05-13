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

import mx.indra.hpqctestlink.beans.Actions;
import mx.indra.hpqctestlink.beans.MTCP;

public class HPQCXLSProcessServiceImpl implements HPQCXLSProcessService {

	private static final Logger LOG = LoggerFactory.getLogger(HPQCXLSProcessServiceImpl.class);
 
	
	
	
	public MTCP processXLS(File excelFile) throws Exception {
		// LOG.info("Ubicacion del archivo de origen : "+args[0]);
		MTCP mtcp = new MTCP();
		ArrayList<Actions> actions = new ArrayList<Actions>();
		
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

				//System.out.println("NUEVA FILA");

				while (cellIterator.hasNext()) {

					Cell celldata = (Cell) cellIterator.next();
					int columnIndex = celldata.getColumnIndex();
					int rowIndex = celldata.getRowIndex();

					System.out.print(rowIndex + " " + columnIndex +"-");
					// System.out.print(rowIndex+ " ");

					switch (celldata.getCellType()) {
					case STRING:

						// GEENERAR LISTA DE ACCIONES (PASOS)
						if (rowIndex >= 2 && columnIndex == 4) {
						    ArrayList<String> listSteps =new ArrayList<String>();

							StringTokenizer tokens = new StringTokenizer(celldata.getStringCellValue(), "\n");
							while (tokens.hasMoreTokens()) {
								//System.out.println("step: " + tokens.nextToken());
						
								listSteps.add(tokens.nextToken());
							
							}

							Actions action = new Actions();
							action.setActions(listSteps);
							actions.add(action);
	
						} else {
							//System.out.print(celldata.getStringCellValue());
						}

						break;
					case NUMERIC:
						//System.out.print(celldata.getNumericCellValue());
						break;
					case BOOLEAN:
						//System.out.print(celldata.getBooleanCellValue());
						break;
					case BLANK:
						//System.out.print("  ");
					default:
						//System.out.print(" - ");
					}

				}
				//System.out.println();
			}

			workbook.close();
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		mtcp.setActions(actions);
		
		return mtcp;
	}

}
