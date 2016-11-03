package br.unibh.seguros.negocio;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Desconto;
import br.unibh.seguros.entidades.Proposta;
import br.unibh.seguros.entidades.Segurado;
import br.unibh.seguros.entidades.TipoCombustivel;


@Stateless
@LocalBean

public class ServicoProposta implements DAO<Proposta, Long> {

	@Inject
	EntityManager em;
	@Inject
	private Logger log;
	@Inject
	private ServicoDesconto sd;

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
	public List<Proposta> findBySegurado(Segurado s) throws Exception {
		
		log.info("Encontrando propostas do segurado "+s.getNome());
		return em.createNamedQuery("Proposta.findBySeguradoID").setParameter("id", s.getId()).getResultList();		
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
			

		if (proposta.getClasse().equals("E")){
			
		proposta.getValorPremio().subtract(new BigDecimal(400F));
					
		}else if (proposta.getClass().equals("C")){
			
			proposta.getValorPremio().subtract(new BigDecimal(300F));
		}else if (proposta.getClass().equals("D")){
			
			proposta.getValorPremio().subtract(new BigDecimal(200F));
		}else if (proposta.getClass().equals("E")){
			
			proposta.getValorPremio().subtract(new BigDecimal(100F));
		}
		Desconto d = sd.findByClasse(proposta.getClasse());
		
		if(d != null){
			
			proposta.getValorPremio().subtract(proposta.getValorPremio().multiply(d.getPercentualDesconto().divide(new BigDecimal(100))));
			
		}
		
		return proposta.getValorPremio();

		
		}	
	
		public BigDecimal calculoValorFranquia(Proposta proposta) throws Exception{
		
			if (proposta == null || proposta.getValorSegurado() == null || proposta.getValorPremio() == null){
				
				return null;
				
			}
		BigDecimal franquia = proposta.getValorSegurado().multiply(new BigDecimal(0.02F));
		
		franquia.add(proposta.getValorPremio().multiply(new BigDecimal (1F/3F)));
				
		return franquia;
		}
				
				
	
		public String calculaValorClasse(Proposta proposta) throws Exception{
			
			if (proposta == null || proposta.getSegurado() == null){
				
				return null;				
			}
			
			List<Proposta> lista = findBySegurado(proposta.getSegurado());
			
				if (lista.isEmpty()){
					
					return "A";
				}
				int maiorClasse = 1;
				int classe = 0;
				for (Proposta p: lista){
					
					if (p.getClasse().equals("E")) classe = 5;
					else if (p.getClasse().equals("D")) classe = 4;
					else if (p.getClasse().equals("C")) classe = 3;
					else if (p.getClasse().equals("B")) classe =2;
					else if (p.getClasse().equals("A")) classe =1;
					
					if (maiorClasse < classe) maiorClasse = classe;
					
					
				}
			if (maiorClasse >= 4 ) return "E";
			else if (maiorClasse == 3) return "D";
			else if (maiorClasse == 2) return "C";
			else if(maiorClasse == 1)  return "B";
			
			return null;
		}
		
		
		public String criarCodigoSUSEP(Proposta proposta)throws Exception {
			
			if (proposta ==null || proposta.getSegurado() == null){
			
				return null;
				
			}
			
			SimpleDateFormat df = new SimpleDateFormat ("YYMMDD");
			String codigo = df.format(proposta.getData());
			codigo += proposta.getSegurado().getEstado();
			String[] nomes = proposta.getSegurado().getNome().split(" ");
			codigo += nomes[0].charAt(0);
			codigo += nomes[0].charAt(1);
			codigo += Math.random()*1000;
			codigo += proposta.getClasse();
			return codigo;
			
		}
		
	}
	