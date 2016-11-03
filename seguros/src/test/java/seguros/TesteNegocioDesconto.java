package seguros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.unibh.seguros.entidades.Desconto;
import br.unibh.seguros.negocio.DAO;
import br.unibh.seguros.negocio.ServicoDesconto;
import br.unibh.seguros.util.Resources;
@Ignore
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TesteNegocioDesconto {

	@Deployment
	public static Archive<?> createTestArchive() {
		// Cria o pacote que vai ser instalado no Wildfly para realizacao dos
		// testes
		return ShrinkWrap.create(WebArchive.class, "testeseguro.war")
				.addClasses(Desconto.class, Resources.class, DAO.class, ServicoDesconto.class)
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	// Realiza as injecoes com CDI
	@Inject
	private Logger log;
	@Inject
	private ServicoDesconto ss;

	@Test
	public void teste01_incluir_com_classe_existente() throws Exception {
		log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Desconto o = new Desconto(1L, "A", new Date(), null, new BigDecimal("15.00"));
		ss.insert(o);
		Desconto o2 = new Desconto(2L, "A", new Date(), null, new BigDecimal("20.00"));
		ss.insert(o2);
		List<Desconto> l = ss.findAllByClasse("A");
		if (l.size() == 0)
			fail("Teste " + Thread.currentThread().getStackTrace()[1].getMethodName()+" Falhou");
		for (Desconto d : l) {
			if (d.getDataFim() != null) {
				assertEquals(new BigDecimal("15.00"), d.getPercentualDesconto());
			} else {

				assertEquals(new BigDecimal("20.00"), d.getPercentualDesconto());
			}

		}
	}

	@Test
	public void teste02_atualizar_com_classe_ativa_existente() throws Exception{
		
		log.info("==========================> Iniciando o teste "+Thread.currentThread().getStackTrace()[1].getMethodName());
		List<Desconto> l = ss.findAllByClasse("A");
		if (l.size() == 0){
			fail("Teste "+ Thread.currentThread().getStackTrace()[1].getMethodName());
			for(Desconto d : l){
				if(d.getDataFim() != null){
					d.setDataFim(null);
					try {
						ss.update(d);
					}catch (Exception e){
						assertTrue(checkString(e, "Está Operação não pode ser realizada"));
					}
				}
			}
		}		
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
