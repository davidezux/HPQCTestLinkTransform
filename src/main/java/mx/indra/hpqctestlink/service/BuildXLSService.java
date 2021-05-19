package mx.indra.hpqctestlink.service;

import java.io.File;

import mx.indra.hpqc.testlink.beans.xls.XLSRoot;
import mx.indra.hpqctestlink.beans.MTCP;

public interface BuildXLSService {
	public boolean buildXLS(XLSRoot xlsRoot, File excelFile) throws Exception;

	public boolean processTestCases(MTCP mtcp, File excelFile) throws Exception;
}
