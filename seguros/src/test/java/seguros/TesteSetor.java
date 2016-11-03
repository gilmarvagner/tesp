package seguros;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.unibh.seguros.entidades.Setor;

@Ignore
public class TesteSetor {
	
	@Test
	public void testeCriacaoSetor(){
		
			Setor o = new Setor(1L,"Recursos Humanos","RH",null,null);
			System.out.println(o);
			Assert.assertNotNull(o);		
		
	}
	
	@Test 
	public void testeComparacaoSetor(){
		
		
		Setor o = new Setor (1L,"Recursos Humanos", "RH", null, null);
		Setor o2 = new Setor (1L,"Recursos Humanos","RH", null, null);
		Assert.assertTrue(o.equals(o2));
		Setor o3 = new Setor (2L,"Operações","op",null,null);
		Assert.assertFalse(o.equals(o3));
		
		
		
		
	}
	
	
}
