package mx.indra.hpqctestlink.main;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.indra.hpqctestlink.beans.MTCP;
import mx.indra.hpqctestlink.beans.Step;
import mx.indra.hpqctestlink.beans.TestCase;
import mx.indra.hpqctestlink.service.BuildXLSServiceImpl;
import mx.indra.hpqctestlink.service.BuildXMLServiceImpl;
import mx.indra.hpqctestlink.service.HPQCXLSProcessServiceImpl;

public class HPQCTestLinkTransform {

	private static final Logger LOG = LoggerFactory.getLogger(HPQCTestLinkTransform.class);
	private static final String EXCEL_FILE_LOCATION = "C:/Users/dalara/Desktop/files3/PR612_MD1_D3585_ModificadoPruebasdeSistema_Ciclo01.xlsm";
     //private static final String EXCEL_FILE_LOCATION = "C:/Users/dalara/Desktop/files2/421 - ET-00143 - MXSAB - 1563 - Domiciliación Emisión/01 - Cuentas y caja/3180/Pruebas de Integración/PR421_MD1_D3180_PruebasdeIntegracion_Ciclo01.xlsm";
	//private static final  String EXCEL_FILE_LOCATION = "C:/Users/dalara/Desktop/files2/421 - ET-00143 - MXSAB - 1563 - Domiciliación Emisión/01 - Cuentas y caja/3180/Pruebas de Regresión/PR421_MD1_D3180_PruebasdeRegresion_Ciclo01.xlsm";

	public static void main(String[] args) {
		// LOG.info("Ubicacion del archivo de origen : "+args[0]);
		// LOG.info("Ubicacion destino: "+args[1]);
		System.setProperty("log4j.configurationFile",  "resources/log4j2.xml");
		String EXCEL_FILE_LOCATION_ARG = args[0];
		File excelFile = new File(EXCEL_FILE_LOCATION_ARG);
		if (excelFile.exists()) {

			if (excelFile.getName().endsWith("xlsx") || excelFile.getName().endsWith("xlsm")) {
				
				HPQCXLSProcessServiceImpl hpqcxlsProcessServiceImpl = new HPQCXLSProcessServiceImpl();

				try {

					MTCP mtcp = hpqcxlsProcessServiceImpl.processXLS(excelFile);
					
					// PRINT TESTCASES
					LOG.info("REGISTROS PROCESADOS " + (mtcp.getTestCases().size()+1));
					//printList(mtcp.getTestCases());
					
					// ORDER TESTCASES
					Collections.sort(mtcp.getTestCases(), new Comparator<TestCase>() {
						public int compare(TestCase a1, TestCase a2) {
							return a1.getNumber().intValue() - a2.getNumber().intValue();
						}
					});


					// GENERA EXCEL
					BuildXLSServiceImpl buildXLSServiceImpl = new BuildXLSServiceImpl();
					buildXLSServiceImpl.processTestCases(mtcp, excelFile);

					// GENERA XML
					String outPutPath = excelFile.getParent() + "/" + excelFile.getName().split("\\.")[0] + ".xml";
					BuildXMLServiceImpl buildXMLServiceImpl = new BuildXMLServiceImpl();
					buildXMLServiceImpl.processTestCases(mtcp, outPutPath);

				} catch (Exception e) {
					System.out.println("Layout no soportado. Revise las consideraciones aplicacbles a la columna E hoja2 (descripcion) que contiene la lista de pasos.");
					//e.printStackTrace();
				}
				
				
			} else if (excelFile.getName().endsWith("xls")) {
				//LOG.info("Formato de archivo Excel version 2003 o anteriores no son validos.");
				System.out.println("El archivo de Excel version 2003 con extension xls no esta soportado , proposione un archivo de Excel 2007 o posterior con extencion xlsx");
			
				

			} else {
				//LOG.info("Formato de archivo no valido.");
				System.out.println("El archivo especificado no es un archivo Excel valido (solo extenciones xlsx son validas)");
				
			}


		} else {
			System.out.println("Archivo no encontrado. Verificar que la ubicacion del archivo espeficifico sea el correcto");
			//LOG.info("Archivo no encontrado. Verificar que la ubicacion del archivo espeficifico sea el correcto");
		}

	}

	private static void printList(List<TestCase> testCases) {
		for (TestCase testCase : testCases) {
			LOG.info("testCase: " + testCase.getName());
			LOG.info("version: " + testCase.getVersion());
			LOG.info("Summary: " + testCase.getSummary());
			LOG.info("Preconditions: " + testCase.getPreconditions());
			LOG.info("ExecutionType: " + testCase.getExecutionType());
			LOG.info("Importance: " + testCase.getImportance());
			LOG.info("EstimatedExecDuration: " + testCase.getEstimatedExecDuration());
			for (Step step : testCase.getSteps()) {
				LOG.info("	 Step: " + step.getStepNumber() + " - " + step.getActions() + " - "
						+ step.getExpectedResults() + " - " + step.getExecutionType2());
			}
		}
	}

}
