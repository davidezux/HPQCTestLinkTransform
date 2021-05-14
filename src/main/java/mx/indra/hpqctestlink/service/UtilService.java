package mx.indra.hpqctestlink.service;

import java.util.ArrayList;
import java.util.List;

import mx.indra.hpqctestlink.beans.Step;

public interface UtilService {
	
	public ArrayList<Step> getSteps(List<String> headers, List<String> steps);

}
