package mx.indra.hpqctestlink.beans.testlink;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TestCase")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCase {

    @XmlElement(name="name")
	private String name;
    
    @XmlElement(name="version")
	private String version;

    @XmlElement(name="summary")
	private String summary;

    @XmlElement(name="preconditions")
	private String preconditions;

    @XmlElement(name="executionType")
	private String executionType;

    @XmlElement(name="importance")
	private String importance;

    @XmlElement(name="estimatedExecDuration")
	private String estimatedExecDuration;

    @XmlElement(name="steps")
	private ArrayList<Step> steps;

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