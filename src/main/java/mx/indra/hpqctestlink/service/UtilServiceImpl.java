package mx.indra.hpqctestlink.service;

import java.util.ArrayList;
import java.util.List;

import mx.indra.hpqctestlink.beans.Step;

public class UtilServiceImpl implements UtilService {
	
    //FUNCION ENCARGADA DE AJUSTAR EL ENCABEZADO DE PASOS SI EL ENCABEZADO CONTIENE MAS DE UNA DESCRIPCION
	public ArrayList<Step> getSteps(List<String> headers,List<String> steps) {
		
		ArrayList<Step> listSteps = new ArrayList<Step>();
		String header= "";
		String aux= "";
		
		//System.out.println("HEADERS SIZE:" + headers.size());
		//System.out.println("HEADERS SIZE:" + headers.get);
		if(headers.size()>=2) {
			for(int i = 0; i < headers.size(); i++ ) {
				if(i!=headers.size()-1) {
					aux+= headers.get(i).substring(0, headers.get(i).length()-1).concat(",");
				}else {
					header+= aux + headers.get(i);
					//System.out.println("aux:" + aux);
					System.out.println("header:" + header);
				}
			}
			
			Step step = new Step();
			step.setStepNumber(new Long(0));
			step.setActions(header);
			listSteps.add(step);
		}else {
			
			Step step = new Step();
			step.setStepNumber(new Long(0));
			step.setActions(headers.get(0));
			listSteps.add(step);
		}
		
		//System.out.println("STEPS SIZE:" + steps.size());
		
		for(int i = 0; i < steps.size(); i++ ) {
			Step step = new Step();
			step.setStepNumber(new Long(i)+1);
			step.setActions(steps.get(i));
			listSteps.add(step);
		}
			
		/*for(int i=0; i < listSteps.size(); i++) {
			System.out.println(listSteps.get(i).getStepNumber());
			System.out.println(listSteps.get(i).getActions());
		}*/
		
		
		return listSteps;
	}

}
