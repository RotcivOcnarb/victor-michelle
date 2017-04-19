package test;

import junit.framework.TestCase;
import model.Empresa;
import service.EmpresaService;

public class CadastraEmpresaTeste extends TestCase{

	public void testCadastra(){
		EmpresaService service = new EmpresaService();
		Empresa em = new Empresa("85748574849403", "Monark lanches", "07:00", "21:00", "20");
		assertEquals(true, service.cadastra(em));
	}
	
}
