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

	public ArrayList<Actions> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Actions> actions) {
		this.actions = actions;
	}
	   
	  
	  
}
