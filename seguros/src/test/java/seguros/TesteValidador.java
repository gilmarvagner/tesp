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

import br.unibh.seguros.entidades.Funcionario;
import br.unibh.seguros.entidades.Proposta;
import br.unibh.seguros.entidades.Segurado;
import br.unibh.seguros.entidades.Setor;

public class TesteValidador {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

	}

	@Test
	public void testeValidacaoSetorOK() {
		Setor o = new Setor(1L, "Recursos Humanos", "RH", null, null);
		System.out.println(o);
		Set<ConstraintViolation<Setor>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Setor> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoSetorErro() {
		Setor o = new Setor(1L, "Recursos Humanos@", "RH", null, null);
		System.out.println(o);
		Set<ConstraintViolation<Setor>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Setor> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testeValidacaoFuncionarioOK() {
		Funcionario o = new Funcionario(1L,"joao","M","11111111111",null, null, null, null, null, null);
		System.out.println(o);
		Set<ConstraintViolation<Funcionario>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Funcionario> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoFuncionarioErro() {
		Funcionario o = new Funcionario(1L,"joao","M","1111111111144",null, null, null, null, null, null);
		System.out.println(o);
		Set<ConstraintViolation<Funcionario>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Funcionario> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
	
	
	
	@Test
	public void testeValidacaoSeguradoOK() {
		Segurado o = new Segurado(1L,"joao","M","11111111111",null, null, null, null, null, null);
		System.out.println(o);
		Set<ConstraintViolation<Segurado>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Segurado> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoSeguradoErro() {
		Segurado o1 = new Segurado(1L,"joao","M","1111111111144",null, null, null, null, null, null);
		System.out.println(o1);
		Set<ConstraintViolation<Segurado>> constraintViolations = validator.validate(o1);
		for (ConstraintViolation<Segurado> c1 : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c1.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
	
	
	
	@Test
	public void testeValidacaoPropostaOK() {
		
		Date data = new Date();
	
		Proposta o = new Proposta(1L, data, "A", "zzzzzzzzzzzzzzz", new BigDecimal("90.00"), new BigDecimal("80.00"), data, data, 0,new BigDecimal("50.00"), 0, "brasil", "1234", "4321", null, null, null, null, null);
		System.out.println(o);
		Set<ConstraintViolation<Proposta>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Proposta> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoPropostaErro() {
		
		Date data = new Date();
		Proposta o1 = new Proposta(1L, data, "A", "zzzzzzzzzzzzzzz99", new BigDecimal("90.00"), new BigDecimal("80.00"), data, data, 0,new BigDecimal("50.00"), 0, "brasil", "1234", "4321", null, null, null, null, null);
		System.out.println(o1);
		Set<ConstraintViolation<Proposta>> constraintViolations = validator.validate(o1);
		for (ConstraintViolation<Proposta> c1 : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c1.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
	
	
	
	
	

	
	
}
