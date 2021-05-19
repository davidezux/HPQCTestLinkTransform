package mx.indra.hpqctestlink.main;

import java.io.File;
import java.io.FileOutputStream;
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
	private static final String EXCEL_FILE_LOCATION = "C:/Users/dalara/Desktop/files2/PR558_MD13_D3480_PruebasdeSistema_Ciclo01.xlsx";

	public static void main(String[] args) {
		// LOG.info("Ubicacion del archivo de origen : "+args[0]);
		// LOG.info("Ubicacion destino: "+args[1]);
		File excelFile = new File(EXCEL_FILE_LOCATION);
		if (excelFile.exists()) {

			if (excelFile.getName().endsWith("xlsx")) {
				
				HPQCXLSProcessServiceImpl hpqcxlsProcessServiceImpl = new HPQCXLSProcessServiceImpl();

				try {

					MTCP mtcp = hpqcxlsProcessServiceImpl.processXLS(excelFile);

					// ORDER TESTCASES
					Collections.sort(mtcp.getTestCases(), new Comparator<TestCase>() {
						public int compare(TestCase a1, TestCase a2) {
							return a1.getNumber().intValue() - a2.getNumber().intValue();
						}
					});

					// PRINT TESTCASES
                    
					LOG.info("REGISTROS PROCESADOS " + (mtcp.getTestCases().size()+1));
					// printList(mtcp.getTestCases());

					// GENERA EXCEL
					BuildXLSServiceImpl buildXLSServiceImpl = new BuildXLSServiceImpl();
					buildXLSServiceImpl.processTestCases(mtcp, excelFile);

					// GENERA XML
					String outPutPath = excelFile.getParent() + "/" + excelFile.getName().split("\\.")[0] + ".xml";
					BuildXMLServiceImpl buildXMLServiceImpl = new BuildXMLServiceImpl();
					buildXMLServiceImpl.processTestCases(mtcp, outPutPath);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
