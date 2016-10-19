package br.unibh.seguros.negocio;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.unibh.seguros.entidades.Proposta;
import br.unibh.seguros.entidades.TipoCombustivel;


@Stateless
@LocalBean

public class ServicoProposta implements DAO<Proposta, Long> {

	@Inject
	EntityManager em;
	@Inject
	private Logger log;
	

	@Override
	public Proposta insert(Proposta t) throws Exception {
		log.info("Persistindo " + t);
		em.persist(t);
		return t;
	}

	@Override
	public Proposta update(Proposta t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	@Override
	public void delete(Proposta t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Proposta find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Proposta.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposta> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Proposta").getResultList();
	}

	@Override
	public List<Proposta> findByName(String name) throws Exception {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Proposta> findByCodigoSusep(String codigoSusep) throws Exception {
		log.info("Encontrando o " + codigoSusep);
		return em.createQuery("Proposta.findByCodigoSusep").setParameter("codigoSusep", codigoSusep + "%").getResultList();
	}

	public BigDecimal calculoValorPremio(Proposta proposta) throws Exception {
		
		
		proposta.getValorPremio().add(new BigDecimal("15000.00"));
		
		
		
		if (proposta.getQuestionario().getSeguradoEPrincpalCondutor()){
			  
		
			proposta.getValorPremio().subtract(new BigDecimal("50.00"))	;
				
				
			
			}
		if (proposta.getQuestionario().getPossuiGaragemResidencia()){
			
			proposta.getValorPremio().subtract(new BigDecimal("50.00"));			
			
		}
		
		if (proposta.getQuestionario().getPossuiGaragenTrabalho()){
			
			proposta.getValorPremio().subtract(new BigDecimal("50.00"));
			
		}
		
		if (proposta.getQuestionario().getPossuiGaragemEstudo()){
			
			proposta.getValorPremio().subtract(new BigDecimal("50.00"));
			
		}
		
		if (proposta.getQuestionario().getPossuiDispositivoAntiFurto()){
			
			proposta.getValorPremio().subtract(new BigDecimal("50.00"));
			
		}
		
		if (proposta.getVeiculo().getZeoKm()){
			
			proposta.getValorPremio().subtract(new BigDecimal("50.00"));
		}
		
		if (proposta.getQuestionario().getUtilizaViculoAtividadesProfissionais()){
			
			proposta.getValorPremio().add(new BigDecimal("75.00"));
			
		}
		
		if (proposta.getQuestionario().getResideComPessoa17A25Anos()){
			
			proposta.getValorPremio().add(new BigDecimal("75.00"));
			
		}
		
		if (proposta.getQuestionario().getAcidenteOuRouboUltimos2Anos()){
			
			proposta.getValorPremio().add(new BigDecimal("75.00"));
			
		}
		
		if (proposta.getVeiculo().getVeiculoAlienado()){
			
			proposta.getValorPremio().add(new BigDecimal("75.00"));
			
		}
		
		if (proposta.getVeiculo().getTipoCombustivel() == TipoCombustivel.DIESEL){
			
			proposta.getValorPremio().add(new BigDecimal("75.00"));
			
		}
		
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		
		if (proposta.getVeiculo().getAnoFabricacao() == anoAtual){
			
			
			proposta.getValorPremio().add(new BigDecimal("75.00"));
			
			
		}
		
		
		int multiplicador = proposta.getQuestionario().getQuilometragemAtual().intValue() % 10000;
			
			proposta.getValorPremio().add(new BigDecimal(5*multiplicador));
			

		if (proposta.getClass().equals("E")){
			
		proposta.getValorPremio().subtract(new BigDecimal(400F));
					
		}else if (proposta.getClass().equals("D")){
			
			proposta.getValorPremio().subtract(new BigDecimal(300F));
		}else if (proposta.getClass().equals("C")){
			
			proposta.getValorPremio().subtract(new BigDecimal(200F));
		}else if (proposta.getClass().equals("F")){
			
			proposta.getValorPremio().subtract(new BigDecimal(100F));
		}
		
		return proposta.getValorPremio();

		
		
	}
	
	
		
	}
	

