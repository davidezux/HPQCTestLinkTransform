package mx.indra.hpqctestlink.main;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.indra.hpqctestlink.beans.MTCP;
import mx.indra.hpqctestlink.beans.testlink.TestCase;
import mx.indra.hpqctestlink.service.BuildXMLServiceImpl;
import mx.indra.hpqctestlink.service.HPQCXLSProcessService;
import mx.indra.hpqctestlink.service.ServiceInjector;

public class HPQCTestLinkTransform {

	private static final Logger LOG = LoggerFactory.getLogger(HPQCTestLinkTransform.class);
	private static final String EXCEL_FILE_LOCATION = "C:/Users/dalara/Desktop/files/PR558_MD13_D3480_PruebasdeSistema_Ciclo01.xlsx";
	private static final String EXCEL_FILE_LOCATION_RESULT = "C:/MTCP_PR558_MD13_D3480_PruebasdeSistema_Ciclo01.xlsx";

	public static void main(String[] args) {


		// String[] args = new String[2];
		// args[0] = "xml";
		// args[1] = "PATH O URL DEL EXCEL ENTRANTE";
		// args[2] = "PATH O URL DEL XML SALIENTE"; 
		if (args.length > 0) {
			if (args[0].equals("xml")) {
				try {
					File excelFile = new File(args[1]);
					BuildXMLServiceImpl buildXMLServiceImpl = new BuildXMLServiceImpl();
					List<TestCase> testCases = buildXMLServiceImpl.readXLS(excelFile);
					buildXMLServiceImpl.processXLS(testCases, args[2]);
				} catch (Exception ex) {

				}
				return;
			}
		} else {

			File excelFile = new File(EXCEL_FILE_LOCATION);
			if (excelFile.exists()) {

				ServiceInjector serviceInjector = new ServiceInjector();
				HPQCXLSProcessService hpqcxlsProcessService = null;
				hpqcxlsProcessService = serviceInjector.getHPQCXLSProcessService();
				try {

					MTCP mtcp = hpqcxlsProcessService.processXLS(excelFile);

					LOG.info("LIST SIZE " + mtcp.getActions().size());

					for (int i = 0; i < mtcp.getActions().size(); i++) {
						System.out.println("i: " + i);

						Iterator<String> it = mtcp.getActions().get(i).getActions().iterator();

						while (it.hasNext()) {
							System.out.println(it.next());
						}

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				LOG.info("Archivo no encontrado. Verificar que la ubicacion del archivo espeficifico sea el correcto");
			}
			
		}
		
		

	}
}
