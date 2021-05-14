package mx.indra.hpqctestlink.service;

public class ServiceInjector {
	
	public HPQCXLSProcessService getHPQCXLSProcessService() {
		return new HPQCXLSProcessServiceImpl();
	}
	
	public UtilService getUtilService() {
		return new UtilServiceImpl();
	}
	
}
