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
import org.junit.Ignore;
import org.junit.Test;

import br.unibh.seguros.entidades.EtapaProcesso;
import br.unibh.seguros.entidades.Funcionario;
import br.unibh.seguros.entidades.Proposta;
import br.unibh.seguros.entidades.Questionario;
import br.unibh.seguros.entidades.Segurado;
import br.unibh.seguros.entidades.Setor;
import br.unibh.seguros.entidades.TipoCombustivel;
import br.unibh.seguros.entidades.TipoDecisao;
import br.unibh.seguros.entidades.Tramitacao;
import br.unibh.seguros.entidades.Veiculo;
@Ignore

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
		
		Date data = new Date();
		Funcionario o = new Funcionario(1L, "Joao Silva", "M", "07719687604", "(31)3340-2900", "(31)3340-2900", "(31)3340-2900", "teste@teste.com.br", data, data, null, null, "Gerente", "gilmarvagner", "gilmar123");
		System.out.println(o);
		Set<ConstraintViolation<Funcionario>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Funcionario> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoFuncionarioErro() {
		Date data = new Date();
		Setor s = new Setor();
		Funcionario o = new Funcionario(1L, "Joao Silva", "C", "07719687604", "(31)3340-2900", "(31)3340-2900", "(31)3340-2900", "teste@teste.com.br", data, data, null, s, "Gerente", "gilmarvagners", "gilmar123");
		System.out.println(o);
		Set<ConstraintViolation<Funcionario>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Funcionario> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
	
	
	
	@Test
	public void testeValidacaoSeguradoOK() {
		Date data = new Date();
		Segurado o = new Segurado(1L, "Ana Silvo", "M", "07719687604", "(31)3340-2900", "(31)3340-2900", "(31)3340-2900", "teste@tese.com.br", data, data, null, "A", "9916920309", "SSP", "1938300000", "A", data, data, "avenida silva", "310", "casa B", "35450-00", "vila velha", "Belo Horizonte", "MG", null);
		
		System.out.println(o);
		Set<ConstraintViolation<Segurado>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Segurado> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoSeguradoErro() {
		Date data = new Date();
		Segurado o = new Segurado(1L, "Joao Silva", "99", "07719687604", "(31)3340-2900", "(31)3340-2900", "(31)3340-2900", "teste@tese.com.br", data, data, null, "B", "16920309", "SSP", "1010101010", "A", data, data, "avenida silva", "310", "casa B", "35450-00", "vila velha", "Belo Horizonte", "MG", null);
		System.out.println(o);
		Set<ConstraintViolation<Segurado>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Segurado> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
	
	
	
	@Test
	public void testeValidacaoPropostaOK() {
		System.out.println("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		Date data = new Date();
	
		Proposta o = new Proposta(1L, data, "A", "A09AAAAAAAAAAAA", new BigDecimal("90.00"), new BigDecimal("80.00"), data, data, 3,new BigDecimal("50.00"), 10, "brasil", "conta", "saldo", null, null, null, null, null);
		System.out.println(o);
		Set<ConstraintViolation<Proposta>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Proposta> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		System.out.println("============> Finalizando o teste " + constraintViolations.size());
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoPropostaErro() {
		
		Date data = new Date();
		Proposta o = new Proposta(1L, data, "A", null, new BigDecimal("90.00"), new BigDecimal("0.01"), data, data, 3,new BigDecimal("0.01"), 10, "brasil", "conta", "saldo", null, null, null, null, null);
		System.out.println(o);
		Set<ConstraintViolation<Proposta>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Proposta> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
	
	
	@Test
	public void testeValidacaoTramitacaoOK() {
		
		
		Tramitacao o = new Tramitacao(1L, null,EtapaProcesso.APROVADA, new Date(20-01-2015), TipoDecisao.AUTOMATICA, null,null, null, null);
		System.out.println(o);
		Set<ConstraintViolation<Tramitacao>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Tramitacao> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoTramitacaoErro() {
		
		
		Tramitacao o = new Tramitacao(1L, null,EtapaProcesso.APROVADA, null, TipoDecisao.AUTOMATICA, null,null, null, null);
		System.out.println(o);
		Set<ConstraintViolation<Tramitacao>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Tramitacao> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
	
	
	
	@Test
	public void testeValidacaoQuestionarioOK() {
		
		
	
		Questionario o = new Questionario(1L, true, "teste", true, true, true, true, true, 1L, true, true, true, null);
		System.out.println(o);
		Set<ConstraintViolation<Questionario>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Questionario> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoQuestionarioErro() {
		
		
		Questionario o = new Questionario(1L, true, "9999", true, true, true, true, true, 1L, true, true, true, null);
		System.out.println(o);
		Set<ConstraintViolation<Questionario>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Questionario> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
	
	
	
	@Test
	public void testeValidacaoVeiculoOK() {
		
		
	
		Veiculo o = new Veiculo(1L, "VW", "gol", 2015, 2016, "PUT-3340", "abcdefghijklea", TipoCombustivel.GASOLINA, true, true, null);
		System.out.println(o);
		Set<ConstraintViolation<Veiculo>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Veiculo> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoVeiculoErro() {
		
		
		Veiculo o = new Veiculo(1L, "VW", "gol", 2016, 2016, "00", "abcdefghijklea", TipoCombustivel.GASOLINA, true, true, null);
		System.out.println(o);
		Set<ConstraintViolation<Veiculo>> constraintViolations = validator.validate(o);
		for (ConstraintViolation<Veiculo> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
	
	
	
	
}
