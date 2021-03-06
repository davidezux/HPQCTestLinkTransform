package mx.indra.hpqctestlink.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.indra.hpqctestlink.beans.MTCP;
import mx.indra.hpqctestlink.beans.testlink.Step;
import mx.indra.hpqctestlink.beans.testlink.TestCase;
import mx.indra.hpqctestlink.beans.testlink.XmlRoot;
import mx.indra.hpqctestlink.converter.MTCPTOXmlRoot;

public class BuildXMLServiceImpl implements BuildXMLService {

	private static final Logger LOG = LoggerFactory.getLogger(BuildXMLServiceImpl.class);

	public List<TestCase> readXLS(File excelFile) throws Exception {

		List<TestCase> testCases = new ArrayList<TestCase>();

		LOG.info("PROCESANDO ARCHIVO : " + excelFile.getName() + " - " + excelFile.getAbsolutePath());
		FileInputStream inputStream = new FileInputStream(excelFile);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		LOG.info("TOTAL DE FILAS : " + firstSheet.getLastRowNum());
		Iterator<Row> iterator = firstSheet.iterator();
		Row headerRow = iterator.next();

		TestCase testCase = null;
		List<Step> steps = null;

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();

			String name = nextRow.getCell(0).getStringCellValue().trim();
			String version = nextRow.getCell(1).getStringCellValue().trim();
			String summary = nextRow.getCell(2).getStringCellValue().trim();
			String preconditions = nextRow.getCell(3).getStringCellValue().trim();
			String executionType = nextRow.getCell(4).getStringCellValue().trim();
			String importance = nextRow.getCell(5).getStringCellValue().trim();
			String estimatedExecDuration = nextRow.getCell(6).getStringCellValue().trim();
			double stepNumber = nextRow.getCell(7).getNumericCellValue();
			String actions = nextRow.getCell(8).getStringCellValue().trim();
			String expectedResults = nextRow.getCell(9).getStringCellValue().trim();
			String executionType2 = nextRow.getCell(10).getStringCellValue().trim();

			// NAME
			if (!name.equals("")) {
				testCase = new TestCase();
				steps = new ArrayList<Step>();
				testCase.setName(name);
				testCase.setVersion(version);
				testCase.setSummary(summary);
				testCase.setPreconditions(preconditions);
				testCase.setExecutionType(executionType);
				testCase.setImportance(importance);
				testCase.setEstimatedExecDuration(estimatedExecDuration);
				testCase.setSteps(steps);
				testCases.add(testCase);
			}
			if (!testCases.equals("")) {
				Step step = new Step();
				step.setStepNumber((int) stepNumber);
				step.setActions(actions);
				step.setExpectedresults(expectedResults);
				step.setExecutionType(executionType2);
				testCase.getSteps().add(step);
			}
		}
		return testCases;
	}

	public boolean processXLS(XmlRoot xmlRoot, String outPutPath) throws Exception {
		try {
			LOG.info("GENERANDO XML");
			JAXBContext jaxbContext = JAXBContext.newInstance(XmlRoot.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.w3.org/2001/XMLSchema-instance");
			jaxbMarshaller.marshal(xmlRoot, new FileOutputStream(outPutPath));
			LOG.info("XML GENERADO CON EXITO");
		} catch (JAXBException e) {
			LOG.info("Whops hubo un error - processXLS - : " + e.getMessage());
			e.printStackTrace();
		}
		return true;
	}

	public boolean processTestCases(MTCP mtcp, String outPutPath) throws Exception {
		processXLS(MTCPTOXmlRoot.processTestCases(mtcp), outPutPath);
		return true;
	}

}
