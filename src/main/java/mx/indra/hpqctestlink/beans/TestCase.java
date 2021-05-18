package mx.indra.hpqctestlink.beans;

import java.util.ArrayList;

public class TestCase {

	private Long number;
	private String name;
	private String version;
	private String summary;
	private String preconditions;
	private String executionType;
	private String importance;
	private String estimatedExecDuration;
	private ArrayList<Step> steps;
	
	
	
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

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

	public ArrayList<Step> getSteps() {
		return steps;
	}

	public void setSteps(ArrayList<Step> steps) {
		this.steps = steps;
	}


}
