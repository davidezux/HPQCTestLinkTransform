package mx.indra.hpqctestlink.service;

import java.io.File;

import mx.indra.hpqctestlink.beans.MTCP;

public interface HPQCXLSProcessService {
	
	public MTCP processXLS(File excelFile) throws Exception;

}
