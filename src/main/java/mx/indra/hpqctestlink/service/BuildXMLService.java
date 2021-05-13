package mx.indra.hpqctestlink.service;

import java.io.File;
import java.util.List;

import mx.indra.hpqctestlink.beans.testlink.TestCase;

 
public interface BuildXMLService {

	public List<TestCase> readXLS(File excelFile) throws Exception;
	
	public boolean processXLS(List<TestCase> testCases, String outPutPath) throws Exception;

}
