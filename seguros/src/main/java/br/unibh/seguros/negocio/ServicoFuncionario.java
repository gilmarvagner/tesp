package br.unibh.seguros.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.seguros.entidades.Funcionario;

@LocalBean
@Stateless

public class ServicoFuncionario implements DAO<Funcionario, Long> {
	@Inject
	EntityManager em;
	@Inject
	private Logger log;

	@Override
	public Funcionario insert(Funcionario t) throws Exception {
		log.info("Persistindo " + t);
		em.persist(t);
		return t;
	}

	@Override
	public Funcionario update(Funcionario t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	@Override
	public void delete(Funcionario t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Funcionario find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Funcionario.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Funcionario").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> findByName(String name) throws Exception {
		log.info("Encontrando o " + name);
		return em.createNamedQuery("Funcionario.findByName").setParameter("nome", name + "%").getResultList();
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Funcionario> findByCpfComSetor(String name) throws Exception {
		log.info("Encontrando o " + name);
		return em.createNamedQuery("Funcionario.findByCpfComSetor").setParameter("cpf", name + "%").getResultList();
	}
	

}
