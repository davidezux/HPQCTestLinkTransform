package mx.indra.hpqctestlink.beans.testlink;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testcases")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRoot {

	@XmlElement(name = "testcase")
	private List<TestCase> testCases = new ArrayList<TestCase>();

	public List<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}

}
