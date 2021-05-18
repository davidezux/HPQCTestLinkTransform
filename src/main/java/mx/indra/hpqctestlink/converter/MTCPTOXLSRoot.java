package mx.indra.hpqctestlink.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import mx.indra.hpqc.testlink.beans.xls.XLSRoot;
import mx.indra.hpqc.testlink.beans.xls.XLSStep;
import mx.indra.hpqc.testlink.beans.xls.XLSTestCaseRoot;
import mx.indra.hpqctestlink.beans.MTCP;

public class MTCPTOXLSRoot {

	public static XLSRoot processTestCases(MTCP mtcp) throws Exception {
		XLSRoot xlsRoot = new XLSRoot();
		List<XLSTestCaseRoot> xlsTestCasesRoot = new ArrayList<XLSTestCaseRoot>();
		
		for (int i = 0; i < mtcp.getTestCases().size(); i++) {
			XLSTestCaseRoot xlsTestCaseRoot = new XLSTestCaseRoot();
			List<XLSStep> xlsSteps = new ArrayList<XLSStep>();
			
			for(int j=0; j < mtcp.getTestCases().get(i).getSteps().size(); j++) {
				
				XLSStep xlsStep = new XLSStep();
				
				if(j==0) {
					
				xlsStep.setName(mtcp.getTestCases().get(i).getName());
				xlsStep.setVersion(mtcp.getTestCases().get(i).getVersion());
				xlsStep.setSummary(mtcp.getTestCases().get(i).getSummary());
				xlsStep.setPreconditions(mtcp.getTestCases().get(i).getPreconditions());
				xlsStep.setExecutionType( mtcp.getTestCases().get(i).getExecutionType());
				xlsStep.setImportance(mtcp.getTestCases().get(i).getImportance());
				xlsStep.setEstimatedExecDuration(mtcp.getTestCases().get(i).getEstimatedExecDuration());
				xlsStep.setStepNumber(String.valueOf(mtcp.getTestCases().get(i).getSteps().get(j).getStepNumber()));
				xlsStep.setActions(mtcp.getTestCases().get(i).getSteps().get(j).getActions());
				xlsStep.setExpectedResults(mtcp.getTestCases().get(i).getSteps().get(j).getExpectedResults());
				xlsStep.setExecutionType2(mtcp.getTestCases().get(i).getSteps().get(j).getExecutionType2());
				
				}else {
					
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
				xlsTestCaseRoot.setXlsSteps(xlsSteps);
				xlsTestCasesRoot.add(xlsTestCaseRoot);
				
			}

		}
		
		
		xlsRoot.setXlsTestCases(xlsTestCasesRoot);


		/*for (XLSTestCaseRoot testCase : xlsRoot.getXlsTestCases()) {
	     
	            int columnCount = 0;

	            for (XLSStep xlsStep : testCase.getXlsSteps()) {
	            	
	            	System.out.println("column : " + columnCount++);
	            	
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
		
	            	
	            	//Cell cell = row.createCell(columnCount++);
	            	//cell.setCellValue(data);
					//cell.setCellStyle(cellStyle);
	            }
	
	        }*/
		
		
		
		
		return xlsRoot;
	}
	
}
