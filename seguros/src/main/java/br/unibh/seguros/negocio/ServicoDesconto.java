package br.unibh.seguros.negocio;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Desconto;



@Stateless
@LocalBean
public class ServicoDesconto implements DAO<Desconto, Long> {

	@Inject
	EntityManager em;
	@Inject
	private Logger log;
	
	@SuppressWarnings("unchecked")
	@Override
	public Desconto insert(Desconto t) throws Exception {
		
		log.info("Encontrando descontos da classe" +t.getClasse());
		List<Desconto> descontos = em.createQuery("select d from Desconto d where d.classe = :classe and d.dataFim is null").setParameter("classe",t.getClasse()).getResultList();
		for(Desconto d: descontos){
			log.info("Desativando desconto existente cujo id = "+t.getId());
			Calendar data = Calendar.getInstance();
			data.add(Calendar.SECOND, 5);
			d.setDataFim(data.getTime());
			
		}
						
		em.flush();
		log.info("Persistindo "+t);
		
		return em.merge(t);
	}

	@Override
	public Desconto update(Desconto t) throws Exception {
		if (t.getDataFim() == null){
		log.info("Encontrando descontos da classe" +t.getClasse());
		@SuppressWarnings("unchecked")
		List<Desconto> descontos = em.createQuery("select d from Desconto d where d.classe = :classe and d.dataFim is null").setParameter("Classe",t.getClasse()).getResultList();
		
		if (descontos.size() > 1){
			
			throw new Exception ("Essa Operação não e possivel ja existe um desconto ativo");
		}
		
		
		}
		
		log.info("Atualizando " + t);
		return em.merge(t);
	
	}

	@Override
	public void delete(Desconto t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Desconto find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Desconto.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Desconto> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Desconto").getResultList();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Desconto> findByName(String classe) throws Exception {
		log.info("Encontrando o " + classe);
		return em.createNamedQuery("Desconto.findByName").setParameter("classe", classe + "%").getResultList();
	}
	
	

	public Desconto findByClasse(String classe) throws Exception {
		log.info("Encontrando o " + classe);
		Object o = em.createNamedQuery("Desconto.findByClasse").setParameter("classe",classe+"%").getSingleResult();
		if (o != null) return (Desconto) o;
		return null;
	}
	
	@SuppressWarnings("unchecked")

	public List<Desconto> findAllByClasse(String classe) throws Exception {
		log.info("Encontrando desconto com classe "+classe);
		return em.createNamedQuery("Desconto.findAllByClasse").setParameter("classe", classe).getResultList();
		
	}
}
