package seguros;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.unibh.seguros.entidades.Segurado;

@Ignore
public class TesteSegurado {
	
	
	@Test
	public void testeCriacaoSegurado(){
		
			Segurado o = new Segurado(1L,"João","M","11111111111",null, null, null, null, null, null);
			System.out.println(o);
			Assert.assertNotNull(o);		
		
	}
	
	@Test 
	public void testeComparacaoSegurado(){
		
		
		Segurado o = new Segurado (1L,"Joao", "M", "11111111111", null, null, null, null, null, null);
		Segurado o2 = new Segurado (1L,"Joao","M", "11111111111", null, null, null, null, null, null);
		Assert.assertTrue(o.equals(o2));
		Segurado o3 = new Segurado (2L,"Maria","F","11111111111",null, null, null, null, null, null);
		Assert.assertFalse(o.equals(o3));
		
		
		
		
	}
	

}
