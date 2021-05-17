package mx.indra.hpqctestlink.converter;

import java.util.ArrayList;
import java.util.List;

import mx.indra.hpqctestlink.beans.MTCP;
import mx.indra.hpqctestlink.beans.testlink.Step;
import mx.indra.hpqctestlink.beans.testlink.TestCase;
import mx.indra.hpqctestlink.beans.testlink.XmlRoot;

public class MTCPTOXmlRoot {

	public static XmlRoot processTestCases(MTCP mtcp) throws Exception {
		XmlRoot xmlRoot = new XmlRoot();
		List<TestCase> testCasesLink = new ArrayList<TestCase>();
		for (mx.indra.hpqctestlink.beans.TestCase testCase : mtcp.getTestCases()) {
			TestCase testCaseLink = new TestCase();
			testCaseLink.setEstimatedExecDuration(testCase.getEstimatedExecDuration());
			testCaseLink.setExecutionType(testCase.getExecutionType());
			testCaseLink.setImportance(testCase.getImportance());
			testCaseLink.setName(testCase.getName());
			testCaseLink.setPreconditions(testCase.getPreconditions());
			testCaseLink.setSummary(testCase.getSummary());
			testCaseLink.setVersion(testCase.getVersion());
			List<Step> steps = new ArrayList<Step>();
			for (mx.indra.hpqctestlink.beans.Step step : testCase.getSteps()) {
				Step stepLink = new Step();
				stepLink.setActions(step.getActions());
				stepLink.setExecutionType(step.getExecutionType2());
				stepLink.setExpectedresults(step.getExpectedResults());
				stepLink.setStepNumber(step.getStepNumber().intValue());
				steps.add(stepLink);
			}
			testCaseLink.setSteps(steps);
			testCasesLink.add(testCaseLink);
		}
		xmlRoot.setTestCases(testCasesLink);
		return xmlRoot;
	}

}
