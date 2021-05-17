package mx.indra.hpqctestlink.beans.testlink;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "name")
@XmlAccessorType(XmlAccessType.FIELD)
public class Step {

	@XmlElement(name="step_number")
	private int stepNumber;

	@XmlElement
	private String actions;

	@XmlElement
	private String expectedresults;

	@XmlElement(name = "execution_type")
	private String executionType;
 

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}
	
	public int getStepNumber() {
		return stepNumber;
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

	public String getExecutionType() {
		return executionType;
	}

	public void setExecutionType(String executionType) {
		this.executionType = executionType;
	}

}
