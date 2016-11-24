package seguros;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.unibh.seguros.entidades.EtapaProcesso;
import br.unibh.seguros.entidades.Funcionario;
import br.unibh.seguros.entidades.Pessoa;
import br.unibh.seguros.entidades.Proposta;
import br.unibh.seguros.entidades.Questionario;
import br.unibh.seguros.entidades.Segurado;
import br.unibh.seguros.entidades.Setor;
import br.unibh.seguros.entidades.TipoCombustivel;
import br.unibh.seguros.entidades.TipoDecisao;
import br.unibh.seguros.entidades.Tramitacao;
import br.unibh.seguros.entidades.Veiculo;
import br.unibh.seguros.negocio.DAO;
import br.unibh.seguros.negocio.ServicoFuncionario;
import br.unibh.seguros.negocio.ServicoSetor;
import br.unibh.seguros.util.Resources;


@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteNegocioFuncionario {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		// Cria o pacote que vai ser instalado no Wildfly para realizacao dos
		// testes
		return ShrinkWrap.create(WebArchive.class, "testeseguro.war")
				.addClasses(Setor.class, Pessoa.class, Funcionario.class, Segurado.class, Proposta.class, Veiculo.class,
						Questionario.class, Tramitacao.class, TipoCombustivel.class, EtapaProcesso.class,
						TipoDecisao.class, Resources.class, DAO.class, ServicoSetor.class, ServicoFuncionario.class)
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	
	// Realiza as injecoes com CDI
		@Inject
		private Logger log;
		
		@Inject
		private ServicoFuncionario sf;
		
		
		@Test
		public void teste01_inserirSemErro() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Funcionario o = new Funcionario(1L, "Joao Silva", "M", "07719687604", "(31)3340-2900", "(31)3340-2900", "(31)3340-2900", "teste@tese.com.br", new Date(), new Date(), null, null, "gilmar", "gilmarvagner", "gilmar123");
			sf.insert(o);
			Funcionario aux = (Funcionario) sf.findByName("joao Silva").get(0);
			assertNotNull(aux);
			log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	
		@Test
		public void teste02_inserirComErro() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			try {
				Funcionario o = new Funcionario(1L, "Joao Silva", null, "07719687604", "(31)3340-2900", "(31)3340-2900", "(31)3340-2900", "teste@tese.com.br", new Date(), new Date(), null, null, "gilmar", "gilmarvagner", "gilmar123");
				sf.insert(o);
			} catch (Exception e) {
				assertTrue(checkString(e, "Caracteres permitidos: letras, espaços, ponto e aspas simples"));
			}
			log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}

	
		@Test
		public void teste03_atualizar() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Funcionario o = (Funcionario) sf.findByName("Joao Silva").get(0);
			o.setNome("Joao Silveira");
			sf.update(o);
			Funcionario aux = (Funcionario) sf.findByName("Joao Silveira").get(0);
			assertNotNull(aux);
			log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}

		@Test
		public void teste04_excluir() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Funcionario s = (Funcionario) sf.findByName("Joao Silveira").get(0);
			sf.delete(s);
			assertEquals(0, sf.findByName("Joao Silveira").size());
			log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}

		

		private boolean checkString(Throwable e, String str) {
			if (e.getMessage().contains(str)) {
				return true;
			} else if (e.getCause() != null) {
				return checkString(e.getCause(), str);
			}
			return false;
		}
	}

	
	
	
	


