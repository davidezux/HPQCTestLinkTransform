package mx.indra.hpqc.testlink.beans.xls;

import java.util.ArrayList;
import java.util.List;

public class XLSRoot {
	
	private List<XLSTestCaseRoot> xlsTestCases = new ArrayList<XLSTestCaseRoot>();

	public List<XLSTestCaseRoot> getXlsTestCases() {
		return xlsTestCases;
	}

	public void setXlsTestCases(List<XLSTestCaseRoot> xlsTestCases) {
		this.xlsTestCases = xlsTestCases;
	}
	
	
}
