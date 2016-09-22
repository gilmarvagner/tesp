
package seguros;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.unibh.seguros.entidades.Desconto;

public class TesteDesconto {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testeCriacaoDesconto() {

		Desconto o = new Desconto(1L, "A", new Date(), null, new BigDecimal("100.00"));

		System.out.println(o);
		Assert.assertNotNull(o);

	}

	@Test
	public void testeComparacaoDesconto() {
		Date data = new Date();
		Desconto o = new Desconto(1L, "A", data, null, new BigDecimal("100.00"));
		Desconto o2 = new Desconto(1L, "A", data, null, new BigDecimal("100.00"));
		Assert.assertTrue(o.equals(o2));
		Desconto o3 = new Desconto(2L, "B", data, null, new BigDecimal("100.00"));
		Assert.assertFalse(o.equals(o3));

	}

	@Test
	public void testeValidacaoDescontoOK() {

		Date data = new Date();
		Desconto o = new Desconto(1L, "A", data, null, new BigDecimal("90.00"));
		System.out.println(o);
		Set<ConstraintViolation<Desconto>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Desconto> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoDescontoErro() {
		Date data = new Date();
		Desconto o = new Desconto(1L, "B1@", data, null, new BigDecimal("100.00"));
		System.out.println(o);
		Set<ConstraintViolation<Desconto>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Desconto> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}

}
