package mx.indra.hpqctestlink.main;

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

import mx.indra.hpqctestlink.beans.Action;

public class HPQCTestLinkTransform {

	private static final Logger LOG = LoggerFactory.getLogger(HPQCTestLinkTransform.class);
	private static final String EXCEL_FILE_LOCATION = "C:/Users/dalara/Desktop/files/PR558_MD13_D3480_PruebasdeSistema_Ciclo01.xlsx";
	
	public static void main(String[] args){
		
		//LOG.info("Ubicacion del archivo de origen : "+args[0]);  
		
		File excelFile = new File(EXCEL_FILE_LOCATION);
		if (excelFile.exists()) {
			try {
				LOG.info("PROCESANDO ARCHIVO: " + excelFile.getName());
				FileInputStream inputStream = new FileInputStream(excelFile);
				Workbook workbook = new XSSFWorkbook(inputStream);
				Sheet firstSheet = workbook.getSheetAt(1);
				Iterator<Row> iterator = firstSheet.iterator();

				while (iterator.hasNext()) {
					Row nextRow = iterator.next();
					Iterator<Cell> cellIterator = nextRow.cellIterator();

					while (cellIterator.hasNext()) {

						Cell celldata = (Cell) cellIterator.next();
				        int columnIndex = celldata.getColumnIndex();
				        int rowIndex= celldata.getRowIndex();
				       
						System.out.print(rowIndex+ " " + columnIndex);
						//System.out.print(rowIndex+ " ");
						
						switch (celldata.getCellType()) {
						case STRING:
							System.out.print(celldata.getStringCellValue());
							
							if(rowIndex==2 && columnIndex == 4) {
						    	   ArrayList<Action> actions = new ArrayList<Action>();
						    	   
						    	   StringTokenizer tokens=new StringTokenizer(celldata.getStringCellValue(), "\n");
						    	   while(tokens.hasMoreTokens()){
						               System.out.println("step: " + tokens.nextToken());
						           }
						    	  // System.out.print("actions " + celldata.getStringCellValue());
						       }
							
							
							break;
						case NUMERIC:
							System.out.print(celldata.getNumericCellValue());
							break;
						case BOOLEAN:
							System.out.print(celldata.getBooleanCellValue());
							break;
						case BLANK:
							System.out.print("  ");
						default:
							System.out.print(" - ");
						}

					}
					System.out.println();
				}

				workbook.close();
				inputStream.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}else {
			LOG.info("Archivo no encontrado. Verificar que la ubicacion del archivo espeficifico sea el correcto");
		}

	}
}
