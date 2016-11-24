package seguros;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.unibh.seguros.entidades.Funcionario;

@Ignore
public class TesteFuncionario {
	
	@Test
	public void testeCriacaoFuncionario(){
		
			Funcionario o = new Funcionario(1L,"joao","M","11111111111",null, null, null, null, null, null);
			System.out.println(o);
			Assert.assertNotNull(o);		
		
	}
	
	@Test 
	public void testeComparacaoFuncionario(){
		
		
		Funcionario o = new Funcionario (1L,"Joao", "M", "11111111111", null, null, null, null, null, null);
		Funcionario o2 = new Funcionario (1L, "Joao", "M", "11111111111", null, null, null, null, null, null);
		Assert.assertTrue(o.equals(o2));
		Funcionario o3 = new Funcionario (2L,"Maria","F", "11111111111", null, null, null, null,null,null);
		Assert.assertFalse(o.equals(o3));
		
		
		
		
	}
	
	

}
