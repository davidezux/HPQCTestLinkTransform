package mx.indra.hpqctestlink.converter;

import java.util.ArrayList;
import java.util.List;

import mx.indra.hpqc.testlink.beans.xls.XLSRoot;
import mx.indra.hpqc.testlink.beans.xls.XLSStep;
import mx.indra.hpqc.testlink.beans.xls.XLSTestCaseRoot;
import mx.indra.hpqctestlink.beans.MTCP;

public class MTCPTOXLSRoot {

	public static XLSRoot processTestCases(MTCP mtcp) throws Exception {
		XLSRoot xlsRoot = new XLSRoot();
		List<XLSStep> xlsSteps = new ArrayList<XLSStep>();

		for (int i = 0; i < mtcp.getTestCases().size(); i++) {

			for (int j = 0; j < mtcp.getTestCases().get(i).getSteps().size(); j++) {

				XLSStep xlsStep = new XLSStep();

				if (j == 0) {

					xlsStep.setName(mtcp.getTestCases().get(i).getName());
					xlsStep.setVersion(mtcp.getTestCases().get(i).getVersion());
					xlsStep.setSummary(mtcp.getTestCases().get(i).getSummary());
					xlsStep.setPreconditions(mtcp.getTestCases().get(i).getPreconditions());
					xlsStep.setExecutionType(mtcp.getTestCases().get(i).getExecutionType());
					xlsStep.setImportance(mtcp.getTestCases().get(i).getImportance());
					xlsStep.setEstimatedExecDuration(mtcp.getTestCases().get(i).getEstimatedExecDuration());
					xlsStep.setStepNumber(String.valueOf(mtcp.getTestCases().get(i).getSteps().get(j).getStepNumber()));
					xlsStep.setActions(mtcp.getTestCases().get(i).getSteps().get(j).getActions());
					xlsStep.setExpectedResults(mtcp.getTestCases().get(i).getSteps().get(j).getExpectedResults());
					xlsStep.setExecutionType2(mtcp.getTestCases().get(i).getSteps().get(j).getExecutionType2());

				} else {

					xlsStep.setName("");
					xlsStep.setVersion("");
					xlsStep.setSummary("");
					xlsStep.setPreconditions("");
					xlsStep.setExecutionType("");
					xlsStep.setImportance("");
					xlsStep.setEstimatedExecDuration("");
					xlsStep.setStepNumber(String.valueOf(mtcp.getTestCases().get(i).getSteps().get(j).getStepNumber()));
					xlsStep.setActions(mtcp.getTestCases().get(i).getSteps().get(j).getActions());
					xlsStep.setExpectedResults(mtcp.getTestCases().get(i).getSteps().get(j).getExpectedResults());
					xlsStep.setExecutionType2(mtcp.getTestCases().get(i).getSteps().get(j).getExecutionType2());

				}

				xlsSteps.add(xlsStep);

			}

		}

		xlsRoot.setXlsSteps(xlsSteps);

		// printList(xlsRoot.getXlsSteps());

		return xlsRoot;
	}

	public static void printList(List<XLSStep> xlsSteps) {

		for (int i = 0; i < xlsSteps.size(); i++) {

			for (XLSStep xlsStep : xlsSteps) {
				System.out.println("name : " + xlsStep.getName());
				System.out.println("version : " + xlsStep.getVersion());
				System.out.println("summary : " + xlsStep.getSummary());
				System.out.println("preconditions: " + xlsStep.getPreconditions());
				System.out.println("executionType: " + xlsStep.getExecutionType());
				System.out.println("importance: " + xlsStep.getImportance());
				System.out.println("estimatedExecDuration: " + xlsStep.getEstimatedExecDuration());
				System.out.println("stepNumber" + xlsStep.getStepNumber());
				System.out.println("actions" + xlsStep.getActions());
				System.out.println("expectedResults" + xlsStep.getExpectedResults());
				System.out.println("executionType2" + xlsStep.getExecutionType2());
			}

		}

	}

}
