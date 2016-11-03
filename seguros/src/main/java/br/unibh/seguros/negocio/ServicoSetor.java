package br.unibh.seguros.negocio;


import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Setor;

@Stateless
@LocalBean
public class ServicoSetor implements DAO<Setor, Long> {
	@Inject
	EntityManager em;
	@Inject
	private Logger log;

	@Override
	public Setor insert(Setor t) throws Exception {
		log.info("Persistindo " + t);
		em.persist(t);
		return t;
	}

	@Override
	public Setor update(Setor t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	@Override
	public void delete(Setor t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Setor find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Setor.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Setor> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Setor").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Setor> findByName(String name) throws Exception {
		log.info("Encontrando o " + name);
		return em.createNamedQuery("Setor.findByName").setParameter("nome", name + "%").getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Setor> findByIDComSetorSuperior(Long id) throws Exception {
		log.info("Encontrando o " + id);
		return em.createNamedQuery("Setor.findByIdComSetorSuperior").setParameter("id", id).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Setor> findByNameComFuncionarios(String nome) throws Exception {
		log.info("Encontrando o " + nome);
		return em.createNamedQuery("Setor.findByNameComFuncionarios").setParameter("nome", nome+ "%").getResultList();
	}
	

}