package mx.indra.hpqctestlink.beans;

import java.util.ArrayList;

public class MTCP {
	
	private String name;
	private String version;
	private String summary;
	private String preconditions;
	private String executionType;
	private String executionType2;
	private String importance;
	private String estimatedExecDuration;
	private String expectedResults;
	private ArrayList<Actions> actions;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(String preconditions) {
		this.preconditions = preconditions;
	}

	public String getExecutionType() {
		return executionType;
	}

	public void setExecutionType(String executionType) {
		this.executionType = executionType;
	}

	public String getExecutionType2() {
		return executionType2;
	}

	public void setExecutionType2(String executionType2) {
		this.executionType2 = executionType2;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getEstimatedExecDuration() {
		return estimatedExecDuration;
	}

	public void setEstimatedExecDuration(String estimatedExecDuration) {
		this.estimatedExecDuration = estimatedExecDuration;
	}

	public String getExpectedResults() {
		return expectedResults;
	}

	public void setExpectedResults(String expectedResults) {
		this.expectedResults = expectedResults;
	}

	

	public ArrayList<Actions> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Actions> actions) {
		this.actions = actions;
	}
	   
	  
	  
}
