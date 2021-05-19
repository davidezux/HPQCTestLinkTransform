package mx.indra.hpqctestlink.beans.testlink;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCase {

	@XmlAttribute
	private String name;

	@XmlElement
	private String version;

	@XmlElement
	private String summary;

	@XmlElement
	private String preconditions;

	@XmlElement(name = "execution_type")
	private String executionType;

	@XmlElement
	private String importance;

	@XmlElement(name = "estimated_exec_duration")
	private String estimatedExecDuration;

	@XmlElementWrapper(name = "steps")
	@XmlElement(name = "step")
	private List<Step> steps = new ArrayList<Step>();

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

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

}