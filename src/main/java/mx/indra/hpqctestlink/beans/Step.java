package mx.indra.hpqctestlink.beans;

public class Step {

	private Long stepNumber;
	private String actions;
	private String expectedResults;
	private String executionType2;

	public Long getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(Long stepNumber) {
		this.stepNumber = stepNumber;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getExpectedResults() {
		return expectedResults;
	}

	public void setExpectedResults(String expectedResults) {
		this.expectedResults = expectedResults;
	}

	public String getExecutionType2() {
		return executionType2;
	}

	public void setExecutionType2(String executionType2) {
		this.executionType2 = executionType2;
	}

}
