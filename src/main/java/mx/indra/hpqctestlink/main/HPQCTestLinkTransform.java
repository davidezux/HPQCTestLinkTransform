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
import mx.indra.hpqctestlink.service.HPQCXLSProcessService;
import mx.indra.hpqctestlink.service.ServiceInjector;

public class HPQCTestLinkTransform {

	private static final Logger LOG = LoggerFactory.getLogger(HPQCTestLinkTransform.class);
	private static final String EXCEL_FILE_LOCATION = "C:/Users/dalara/Desktop/files/PR558_MD13_D3480_PruebasdeSistema_Ciclo01.xlsx";

	public static void main(String[] args) {
		// LOG.info("Ubicacion del archivo de origen : "+args[0]);
		// LOG.info("Ubicacion destino: "+args[1]);
		File excelFile = new File(EXCEL_FILE_LOCATION);
		if (excelFile.exists()) {

			ServiceInjector serviceInjector = new ServiceInjector();
			HPQCXLSProcessService hpqcxlsProcessService = null;
			hpqcxlsProcessService = serviceInjector.getHPQCXLSProcessService();
			try {

				MTCP mtcp = hpqcxlsProcessService.processXLS(excelFile);

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

				/*
				 * LOG.info("LIST SIZE " + mtcp.getTestCases().size());
				 * 
				 * for (int i = 0; i < mtcp.getTestCases().size(); i++) {
				 * System.out.println("Test case : " + i); System.out.println("name : " +
				 * mtcp.getTestCases().get(i).getName()); System.out.println("version : " +
				 * mtcp.getTestCases().get(i).getVersion()); System.out.println("summary : " +
				 * mtcp.getTestCases().get(i).getSummary());
				 * System.out.println("preconditions: " +
				 * mtcp.getTestCases().get(i).getPreconditions());
				 * System.out.println("executionType: " +
				 * mtcp.getTestCases().get(i).getExecutionType());
				 * System.out.println("importance: " +
				 * mtcp.getTestCases().get(i).getImportance());
				 * System.out.println("estimatedExecDuration: " +
				 * mtcp.getTestCases().get(i).getEstimatedExecDuration());
				 * System.out.println("steps: ");
				 * 
				 * /*for(int j=0; j < mtcp.getTestCases().get(i).getSteps().size(); j++) {
				 * System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).getStepNumber
				 * ());
				 * System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).getActions())
				 * ; System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).
				 * getExpectedResults());
				 * System.out.println(mtcp.getTestCases().get(i).getSteps().get(j).
				 * getExecutionType2()); }
				 * 
				 * }
				 */

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			LOG.info("Archivo no encontrado. Verificar que la ubicacion del archivo espeficifico sea el correcto");
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
