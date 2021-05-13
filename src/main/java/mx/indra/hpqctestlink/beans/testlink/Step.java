package mx.indra.hpqctestlink.beans.testlink;

public class Step {

	private Long step_number;
	private String actions;
	private String expectedresults;
	private String execution_type;
	
	public Long getStep_number() {
		return step_number;
	}
	public void setStep_number(Long step_number) {
		this.step_number = step_number;
	}
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	public String getExpectedresults() {
		return expectedresults;
	}
	public void setExpectedresults(String expectedresults) {
		this.expectedresults = expectedresults;
	}
	public String getExecution_type() {
		return execution_type;
	}
	public void setExecution_type(String execution_type) {
		this.execution_type = execution_type;
	}
	
	
	
}
