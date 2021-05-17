package mx.indra.hpqctestlink.service;

import java.io.File;
import java.util.List;

import mx.indra.hpqctestlink.beans.MTCP;
import mx.indra.hpqctestlink.beans.testlink.TestCase;
import mx.indra.hpqctestlink.beans.testlink.XmlRoot;

public interface BuildXMLService {

	public List<TestCase> readXLS(File excelFile) throws Exception;

	public boolean processXLS(XmlRoot xmlRoot, String outPutPath) throws Exception;

	public boolean processTestCases(MTCP mtcp, String outPutPath) throws Exception;

}
