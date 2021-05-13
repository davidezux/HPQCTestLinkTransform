package mx.indra.hpqctestlink.service;

public class ServiceInjector {
	
	public HPQCXLSProcessService getHPQCXLSProcessService() {
		return new HPQCXLSProcessServiceImpl();
	}
	
	
	
}
